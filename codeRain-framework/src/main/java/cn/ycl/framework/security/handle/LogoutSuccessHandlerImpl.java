package cn.ycl.framework.security.handle;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.constant.HttpStatus;
import cn.ycl.common.core.domain.AjaxResult;
import cn.ycl.common.core.domain.model.LoginUser;
import cn.ycl.common.utils.ServletUtils;
import cn.ycl.common.utils.StringUtils;
import cn.ycl.framework.manager.AsyncManager;
import cn.ycl.framework.manager.factory.AsyncFactory;
import cn.ycl.framework.web.service.TokenService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义处理退出类  返回成功
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private TokenService tokenService;
    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 退出处理
     * @param request 请求
     * @param response 响应
     * @param authentication 认证对象
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)){
            // 处理springSecurity 过滤器链没有走SpringMVC的processRequest 导致request没有设置到RequestContextHolder中  因此后续会获取报空指针
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request, response));
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
