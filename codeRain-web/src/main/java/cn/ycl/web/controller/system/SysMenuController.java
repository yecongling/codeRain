package cn.ycl.web.controller.system;

import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysMenu;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据菜单编号获取详细信息
     * @param menuId 菜单编号
     * @return 菜单信息
     */
    @GetMapping("/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId){
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    /**
     * 修改菜单
     * @param menu 菜单信息
     * @return 返回修改成功失败
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu){
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))){
            return AjaxResult.error("'修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在'");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())){
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return toAjax(menuService.updateMenu(menu));
    }
}
