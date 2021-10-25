package cn.ycl.web.controller.system;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.core.domain.model.LoginBody;
import cn.ycl.common.utils.SecurityUtils;
import cn.ycl.framework.web.service.SysLoginService;
import cn.ycl.system.service.ISysMenuService;
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

    private ISysMenuService menuService;
    @Autowired
    public void setMenuService(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @PostMapping("/login")
    public AjaxResult ajaxLogin(@RequestBody LoginBody user){
        AjaxResult ajax = AjaxResult.success();
        String token = "";
        // 生成令牌
        try {
            token = loginService.login(user.getUsername(), user.getPassword(), user.getCode(),user.getUuid());
            ajax.put(Constants.TOKEN, token);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return ajax;
    }

    /**
     * 获取用户信息
     * @return 返回用户信息
     */
    @PostMapping("getInfo")
    public AjaxResult getInfo(){
        AjaxResult result = AjaxResult.success();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        result.put("user", user);
        return result;
    }

    /**
     * 获取路由配置
     * @return 返回路由配置
     */
    @PostMapping("/getRouters")
    public AjaxResult getRouters(){
        Long userId = SecurityUtils.getUserId();
        return AjaxResult.success();
    }

    @GetMapping("logout")
    public String loginOut(){
        return "/login";
    }
}
