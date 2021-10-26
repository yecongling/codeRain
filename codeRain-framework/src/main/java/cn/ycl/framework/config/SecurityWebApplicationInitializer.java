package cn.ycl.framework.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
