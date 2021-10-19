package cn.ycl.web.controller.system;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.framework.shiro.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * RestController 注解相当于  ResponseBody+Controller   但该注解不能返回页面
 */
@RestController
public class SystemLoginController extends BaseController {

    private SysLoginService loginService;
    @Autowired
    public void setLoginService(SysLoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public AjaxResult ajaxLogin(@RequestBody SysUser user){
        AjaxResult ajax = AjaxResult.success();
        try {
            String token = loginService.login(user.getUserName(), user.getPassword());
            ajax.put(Constants.TOKEN, token);
        } catch (Exception e) {
            ajax = AjaxResult.error(e.getMessage());
        }
        return ajax;
    }

    @GetMapping("logout")
    public String loginOut(){
        return "login/login";
    }
}
