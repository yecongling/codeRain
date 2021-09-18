package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.utils.ShiroUtils;
import cn.ycl.system.domain.SysMenu;
import cn.ycl.system.domain.ZTree;
import cn.ycl.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
