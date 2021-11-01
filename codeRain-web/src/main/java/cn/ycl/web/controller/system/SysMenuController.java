package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysMenu;
import cn.ycl.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    public ISysMenuService menuService;

    @Autowired
    public void setSysMenuService(ISysMenuService sysMenuService) {
        this.menuService = sysMenuService;
    }

    @GetMapping("/list")
    public AjaxResult getMenuList(SysMenu menu){
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return AjaxResult.success(menus);
    }

}
