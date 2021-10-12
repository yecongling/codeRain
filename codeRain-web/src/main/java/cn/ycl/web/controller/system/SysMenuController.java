package cn.ycl.web.controller.system;

import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.utils.ShiroUtils;
import cn.ycl.system.domain.SysMenu;
import cn.ycl.system.domain.ZTree;
import cn.ycl.system.service.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    public ISysMenuService sysMenuService;

    @Autowired
    public void setSysMenuService(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }


    /**
     * 新增保存菜单
     */
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysMenu menu){
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(sysMenuService.checkMenuNameUnique(menu))){
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        // 设置创建人
        menu.setCreateBy(ShiroUtils.getLoginName());
        //AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(sysMenuService.insertMenu(menu));
    }

    @GetMapping()
    public String menu() {
        return "system/menu/menu";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<SysMenu> list(SysMenu menu){
        Long userId = ShiroUtils.getUserId();
        return sysMenuService.selectMenuList(menu, userId);
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId){
        System.out.println(parentId);
        return "system/menu/add";
    }

    /**
     * 校验菜单名称
     * @param menu 菜单对象
     * @return 返回是否唯一
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(SysMenu menu){
        return sysMenuService.checkMenuNameUnique(menu);
    }

    /**
     * 加载所有菜单列表数
     * @return 返回所有菜单列表
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<ZTree> getMenuTreeData(){
        Long userId = ShiroUtils.getUserId();
        return sysMenuService.menuTreeData(userId);
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId){
        //mmap.put("menu", menuService.selectMenuById(menuId));
        return "system/menu/tree";
    }
}
