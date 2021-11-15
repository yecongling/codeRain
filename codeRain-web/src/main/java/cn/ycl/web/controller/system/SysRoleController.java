package cn.ycl.web.controller.system;

import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysRole;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.core.domain.model.LoginUser;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.framework.web.service.SysPermissionService;
import cn.ycl.framework.web.service.TokenService;
import cn.ycl.system.service.ISysRoleService;
import cn.ycl.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    private TokenService tokenService;
    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private ISysRoleService roleService;
    @Autowired
    public void setRoleService(ISysRoleService roleService) {
        this.roleService = roleService;
    }

    private SysPermissionService permissionService;
    @Autowired
    public void setPermissionService(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    private ISysUserService userService;
    @Autowired
    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }

    /**
     * 角色列表
     * @param role 筛选条件
     * @return 角色列表
     */
    @GetMapping("/list")
    public TableDataInfo getRolesList(SysRole role){
        startPage();
        List<SysRole> roles = roleService.selectRoleList(role);
        return getDataTable(roles);
    }

    /**
     * 根据角色编号获取详细信息
     * @param roleId 角色ID
     * @return 角色详细信息
     */
    @GetMapping("/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId){
        roleService.checkRoleDataScope(roleId);
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 修改保存角色
     * @param role 角色信息
     * @return 返回保存成功或失败
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRole role){
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))){
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败, 角色权限已存在");
        }

        role.setUpdateBy(getUsername());
        if (roleService.updateRole(role) > 0){
            // 更新缓存用户权限
            LoginUser loginUser = getLoginUser();
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()){
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "’失败，请联系管理员");
    }

    public TableDataInfo allocatedList(SysUser user) {
        return null;
    }


}
