package cn.ycl.web.controller.system;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.model.LoginBody;
import cn.ycl.framework.shiro.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResult ajaxLogin(@RequestBody LoginBody loginBody){
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @GetMapping("logout")
    public String loginOut(){
        return "login/login";
    }
}
