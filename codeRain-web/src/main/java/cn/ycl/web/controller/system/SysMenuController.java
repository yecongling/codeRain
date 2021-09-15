package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.system.domain.SysMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    @PostMapping("/list")
    @ResponseBody
    public List<SysMenu> list(SysMenu menu){
        //Long userId = ShiroUtils.getUserId();
        //List<SysMenu> menuList = menuService.selectMenuList(menu, userId);
        return null;
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId){
        System.out.println(parentId);
        return "system/menu/add";
    }
}
