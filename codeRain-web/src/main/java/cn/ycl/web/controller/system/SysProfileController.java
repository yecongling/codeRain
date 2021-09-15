package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysProfileController extends BaseController {

    private String prefix = "system/user/profile";

    @RequestMapping("/system/user/profile")
    public String profile(ModelMap map){
        return prefix + "/profile";
    }
}
