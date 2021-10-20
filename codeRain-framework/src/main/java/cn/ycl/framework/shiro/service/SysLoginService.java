package cn.ycl.framework.shiro.service;

import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.exception.user.UserNotExistsException;
import cn.ycl.framework.web.service.TokenService;
import cn.ycl.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService){
        this.tokenService = tokenService;
    }


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
    public String login(String username, String password) throws Exception {

        // 用户名或密码错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }

        return tokenService.createToken(new SysUser());
    }
}
