package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysRole;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.framework.web.service.TokenService;
import cn.ycl.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public TableDataInfo getRolesList(SysRole role){
        startPage();
        List<SysRole> roles = roleService.selectRoleList(role);
        return getDataTable(roles);
    }
}
