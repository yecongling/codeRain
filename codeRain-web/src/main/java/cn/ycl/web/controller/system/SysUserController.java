package cn.ycl.web.controller.system;

import cn.ycl.common.core.controller.BaseController;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.core.page.TableDataInfo;
import cn.ycl.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息controller
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    private ISysUserService userService;
    @Autowired
    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户列表
     * @param user 筛选条件
     * @return 用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUser user){
        startPage();
        List<SysUser> users = userService.selectUserList(user);
        return getDataTable(users);
    }
}
