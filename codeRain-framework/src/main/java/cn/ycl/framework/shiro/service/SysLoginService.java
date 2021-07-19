package cn.ycl.framework.shiro.service;

import cn.ycl.common.core.domain.entity.SysUser;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @return 返回用户信息
     */
    public SysUser login(String username, String password){
        return null;
    }

}
