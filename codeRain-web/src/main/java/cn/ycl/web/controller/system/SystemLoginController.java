package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录验证
 */
@Controller
public class SystemLoginController extends BaseController {

    @PostMapping("/login.do")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe){
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//        } catch (AuthenticationException e){
//
//        }
        return success();
    }

    @GetMapping("logout.do")
    public String loginOut(){
        return "login/login";
    }
}
