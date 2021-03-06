package cn.ycl.framework.config;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.common.utils.spring.SpringUtils;
import cn.ycl.framework.shiro.realm.UserRealm;
import cn.ycl.framework.shiro.session.OnlineSessionDAO;
import cn.ycl.framework.shiro.session.OnlineSessionFactory;
import cn.ycl.framework.shiro.web.session.OnlineWebSessionManager;
import cn.ycl.framework.shiro.web.session.SpringSessionValidationScheduler;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * 权限配置加载
 */
@Configuration
public class ShiroConfig {

    /**
     * Session超时时间，单位为毫秒（默认30分钟）
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
     */
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    /**
     * 同一个用户最大会话数
     */
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    /**
     * 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
     */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    /**
     * 验证码类型
     */
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    /**
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 设置cipherKey密钥
     */
    @Value("${shiro.cookie.cipherKey}")
    private String cipherKey;

    /**
     * 登录地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 缓存管理器 使用Ehcache实现
     *
     * @return
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("coderain");
        EhCacheManager manager = new EhCacheManager();
        if (StringUtils.isNull(cacheManager)) {
            manager.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
        } else {
            manager.setCacheManager(cacheManager);
        }
        System.out.println("缓存管理器注册完成");
        return manager;
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     */
    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            return new ByteArrayInputStream(b);
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 自定义Realm
     */
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthorizationCacheName(Constants.SYS_AUTH_CACHE);
        userRealm.setCacheManager(cacheManager);
        System.out.println("自定义realm完成");
        return userRealm;
    }

    /**
     * 自定义sessionDAO会话
     */
    @Bean
    public OnlineSessionDAO sessionDAO() {
        return new OnlineSessionDAO();
    }

    /**
     * 自定义sessionFactory会话
     */
    @Bean
    public OnlineSessionFactory sessionFactory() {
        return new OnlineSessionFactory();
    }

    /**
     * 会话管理器
     */
    @Bean
    public OnlineWebSessionManager sessionManager() {
        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout((long) expireTime * 60 * 1000);
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
        manager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        System.out.println("会话管理器注册完成");
        return manager;
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 设置realm
        manager.setRealm(userRealm);
        // 注入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 注入session管理器
        manager.setSessionManager(sessionManager());
        System.out.println("注册安全管理器完成");
        return manager;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/pages/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        // 退出 logout地址 shiro去清除session
        filterChainDefinitionMap.put("/logout.do", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login.do", "anon,captchaValidate");
        // 注册相关
        filterChainDefinitionMap.put("/register.do", "anon,captchaValidate");

        LinkedHashMap<String, Filter> filters = new LinkedHashMap<String, Filter>();

        //filters.put("onlineSession", on)

        System.out.println("注册shiro过滤器完成");
        return shiroFilterFactoryBean;
    }

    /**
     * 自定义在线用户处理过滤器
     */
    /*public OnlineSessionFilter onlineSessionFilter(){
        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setLoginUrl(loginUrl);
        onlineSessionFilter.setOnlineSessionDAO(sessionDAO());
        return onlineSessionFilter;
    }*/
}
