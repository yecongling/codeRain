package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 */
@Controller
public class SysRegisterController extends BaseController {

    @GetMapping("/register.do")
    public String register() {
        return "register/register";
    }

    @PostMapping("/register.do")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user){
        return success();
    }

}
