package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.system.domain.SysMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    @PostMapping("/list.do")
    @ResponseBody
    public List<SysMenu> list(SysMenu menu){
        //Long userId = ShiroUtils.getUserId();
        //List<SysMenu> menuList = menuService.selectMenuList(menu, userId);
        return null;
    }
}
