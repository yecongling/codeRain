package cn.ycl.framework.shiro.service;

import cn.ycl.common.core.domain.entity.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

@Component
public class SysPasswordService {

    /**
     * 用户密码验证
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password){

    }

    /**
     * 加密
     * @param loginName
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String loginName, String password, String salt){
        return new Md5Hash(loginName + password + salt).toHex();
    }
}
