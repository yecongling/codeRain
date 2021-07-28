package cn.ycl.framework.shiro.service;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.constant.ShiroConstants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.exception.user.UserPasswordNotMatchException;
import cn.ycl.common.exception.user.UserPasswordRetryLimitExceedException;
import cn.ycl.common.utils.MessageUtils;
import cn.ycl.framework.manager.AsyncManager;
import cn.ycl.framework.manager.factory.AsyncFactory;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SysPasswordService {


    private CacheManager cacheManager;

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private Cache<String, AtomicInteger> loginRecordCache;

    /**
     * 最大重试次数
     */
    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init(){
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    /**
     * 用户密码验证
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password){
        String loginName = user.getLoginName();
        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null){
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.parseInt(maxRetryCount)){
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.parseInt(maxRetryCount));
        }
        // 密码不匹配
        if (!matches(user, password)){
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(loginName);
        }
    }

    /**
     * 匹配用户密码
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(SysUser user, String newPassword){
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    /**
     * 清除登录记录缓存
     * @param loginName
     */
    public void clearLoginRecordCache(String loginName){
        loginRecordCache.remove(loginName);
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
