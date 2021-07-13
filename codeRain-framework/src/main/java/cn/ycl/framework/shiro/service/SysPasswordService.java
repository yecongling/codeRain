package cn.ycl.framework.shiro.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

@Component
public class SysPasswordService {

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
