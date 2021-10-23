package cn.ycl.framework.web.service;

import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.system.service.ISysMenuService;
import cn.ycl.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@Component
public class SysPermissionService {

    private ISysRoleService roleService;
    @Autowired
    public void setRoleService(ISysRoleService roleService) {
        this.roleService = roleService;
    }

    private ISysMenuService menuService;
    @Autowired
    public void setMenuService(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.add("*:*:*");
            // 暂时这样处理 避免没有做selectMenuPermsByUserId 返回null引起的空指针问题
            //perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
