package cn.ycl.framework.shiro.service;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.constant.UserConstants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.enums.UserStatus;
import cn.ycl.common.exception.user.UserBlockedException;
import cn.ycl.common.exception.user.UserDeleteException;
import cn.ycl.common.exception.user.UserNotExistsException;
import cn.ycl.common.exception.user.UserPasswordNotMatchException;
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
 * 登录校验方法
 */
@Component
public class SysLoginService {

    private SysPasswordService passwordService;

    @Autowired
    public void setPasswordService(SysPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    private ISysUserService userService;

    @Autowired
    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录校验
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    public SysUser login(String username, String password) {

        // 用户名或密码错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }

        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        // 验证密码
        passwordService.validate(user, password);

        // 记录登录日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user){
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
