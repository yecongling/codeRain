package cn.ycl.framework.shiro.web.session;

import cn.ycl.common.utils.Threads;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 自定义任务调度器
 */
@Component
public class SpringSessionValidationScheduler implements SessionValidationScheduler {

    private static final Logger log = LoggerFactory.getLogger(SpringSessionValidationScheduler.class);

    public static final long DEFAULT_SESSION_VALIDATION_INTERVAL = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    /**
     * 定时器，用于处理超时的挂起请求，也用于连接断开时的重连。
     */

    @Qualifier("scheduledExecutorService")
    private ScheduledExecutorService executorService;

    @Autowired
    public void setExecutorService(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 会话验证管理器
     * 这里不能使用构造器注入的原因是bean创建的先后顺序问题吗，使用构造器注入会在创建sessionManager bean之前调用，会找不到
     */

    @Qualifier("sessionManager")
    @Lazy
    @Autowired
    private ValidatingSessionManager sessionManager;


    // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
    @Value("${shiro.session.validationInterval}")
    private long sessionValidationInterval;

    private volatile boolean enabled = false;

    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Specifies how frequently (in milliseconds) this Scheduler will call the
     * {@link org.apache.shiro.session.mgt.ValidatingSessionManager#validateSessions()
     * ValidatingSessionManager#validateSessions()} method.
     *
     * <p>
     * Unless this method is called, the default value is {@link #DEFAULT_SESSION_VALIDATION_INTERVAL}.
     *
     * @param sessionValidationInterval
     */
    public void setSessionValidationInterval(long sessionValidationInterval){
        this.sessionValidationInterval = sessionValidationInterval;
    }

    /**
     * Starts session validation by creating a spring PeriodicTrigger.
     */

    public void enableSessionValidation() {
        enabled = true;

        if (log.isDebugEnabled()){
            log.debug("Scheduling session validation job using Spring Scheduler with "
                    + "session validation interval of [" + sessionValidationInterval + "]ms...");
        }

        try {
            executorService.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    if (enabled) {
                        sessionManager.validateSessions();
                    }
                }
            }, 1000, sessionValidationInterval * 60 * 1000, TimeUnit.MILLISECONDS);

            this.enabled = true;
            if (log.isDebugEnabled()){
                log.debug("Session validation job successfully scheduled with Spring Scheduler.");
            }
        } catch (Exception e){
            if (log.isErrorEnabled()){
                log.error("Error starting the Spring Scheduler session validation job.  Session validation may not occur.", e);
            }
        }
    }

    /**
     * 关闭session验证
     */
    public void disableSessionValidation() {
        if (log.isDebugEnabled())
        {
            log.debug("Stopping Spring Scheduler session validation job...");
        }

        if (this.enabled)
        {
            Threads.shutdownAndAwaitTermination(executorService);
        }
        this.enabled = false;
    }
}
