package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.framework.shiro.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 */
@Controller
public class SysRegisterController extends BaseController {


    private SysRegisterService registerService;

    @Autowired
    public void setRegisterService(SysRegisterService registerService){
        this.registerService = registerService;
    }

    @GetMapping("/register.do")
    public String register() {
        return "register/register";
    }

    @PostMapping("/register.do")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user){
        String msg = null;
        try {
            msg = registerService.register(user);
        } catch (Exception e) {

            // 写日志文件
            e.printStackTrace();
        }
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

}
