package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.framework.shiro.service.SysPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysIndexController extends BaseController {

    private SysPasswordService passwordService;

    @Autowired
    public void setPasswordService(SysPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /**
     * 主页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        String index = "index";
        return index;
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("/system/main")
    public String main(){
        return "main";
    }

    /**
     * 锁屏
     * @return
     */
    @GetMapping("/lockscreen")
    public String lockscreen(){
        return "lock";
    }

    /**
     * 解锁屏幕
     * @param password 输入密码
     * @return 返回成功或者失败
     */
    @PostMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password){

        return AjaxResult.error("密码输入不正确，请重新输入");
    }
}
