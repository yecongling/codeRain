package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 */
@Controller
public class SystemLoginController extends BaseController {


    @GetMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
//        if (ServletUtils.isAjaxRequest(request)){
//            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
//        }

        return "login/login";
    }

    @PostMapping("/login.do")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e){
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())){
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("logout.do")
    public String loginOut(){
        return "login/login";
    }
}
