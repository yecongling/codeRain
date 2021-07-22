package cn.ycl.framework.shiro.service;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.utils.DateUtils;
import cn.ycl.common.utils.MessageUtils;
import cn.ycl.common.utils.ShiroUtils;
import cn.ycl.framework.manager.AsyncManager;
import cn.ycl.framework.manager.factory.AsyncFactory;
import cn.ycl.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注册校验方法
 */
@Component
public class SysRegisterService {



    private ISysUserService userService;

    @Autowired
    public void setUserService(ISysUserService userService){
        this.userService = userService;
    }


    private SysPasswordService passwordService;

    @Autowired
    public void setUserService(SysPasswordService passwordService){
        this.passwordService = passwordService;
    }

    /**
     * 注册
     * @param user 用户信息
     * @return
     */
    public String register(SysUser user){
        String msg = "", loginName = user.getLoginName(), password = user.getPassword();

        if (StringUtils.isEmpty(loginName)){
            msg = "用户名不能为空";
        } else if(StringUtils.isEmpty(password)){
            msg = "用户密码不能为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH){
            msg = "密码长度必须在5到20个字符之间";
        } else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH || loginName.length() > UserConstants.PASSWORD_MAX_LENGTH){
            msg = "账户长度在2到20个字符之间";
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkLoginNameUnique(loginName))){
            msg = "注册用户" + loginName + "失败，该注册账户已存在!";
        } else {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));

            boolean  regFlag = userService.registerUser(user);
            if (!regFlag){
                msg = "注册失败，请联系管理员处理";
            } else {
                // 记录登录信息
                AsyncManager.me().execute(AsyncFactory.recordLogininfo(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

}
