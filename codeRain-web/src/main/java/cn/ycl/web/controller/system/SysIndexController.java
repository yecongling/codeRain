package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysIndexController extends BaseController {

    /**
     * 主页
     * @return
     */
    @GetMapping("/index.do")
    public String index(){
        String index = "index";
        return index;
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("/system/main.do")
    public String main(){
        return "main";
    }

    /**
     * 加载菜单
     * @return 返回菜单数据
     */
    @PostMapping("/system/menu.do")
    @ResponseBody
    public AjaxResult getMenu() {
        AjaxResult result = new AjaxResult(AjaxResult.Type.SUCCESS, "成功！");
        result.put("menu", "a");
        return result;
    }

    /**
     * 锁屏
     * @return
     */
    @GetMapping("/lockscreen.do")
    public String lockscreen(){
        return "lock";
    }

    /**
     * 锁屏
     * @return
     */
    @PostMapping("/unlockscreen.do")
    @ResponseBody
    public AjaxResult unlockscreen(){
        return success();
    }
}
