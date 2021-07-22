package cn.ycl.framework.shiro.session;

import cn.ycl.common.utils.IpUtils;
import cn.ycl.common.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义sessionFactory会话
 */
public class OnlineSessionFactory implements SessionFactory {

    public Session createSession(SessionContext initData) {

        OnlineSession onlineSession = new OnlineSession();
        if (initData instanceof WebSessionContext){
            WebSessionContext sessionContext = (WebSessionContext) initData;

            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null){
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                onlineSession.setHost(IpUtils.getIpAddr(request));
                onlineSession.setBrowser(browser);
                onlineSession.setOs(os);
            }
        }
        return onlineSession;
    }
}
