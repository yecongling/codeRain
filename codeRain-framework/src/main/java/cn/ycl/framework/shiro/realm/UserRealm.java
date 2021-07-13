package cn.ycl.framework.shiro.realm;

import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.utils.ShiroUtils;
import cn.ycl.framework.shiro.service.SysPasswordService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm 处理登录权限
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysPasswordService passwordService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();
        char[] password = upToken.getPassword();
        System.out.println(username);
        System.out.println(new String(password));
        SysUser user = new SysUser("admin", "admin123");
        String salt = ShiroUtils.randomSalt();
        ByteSource bytes = ByteSource.Util.bytes(salt);

        String password1 = passwordService.encryptPassword(username, new String(password), salt);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password1, bytes, getName());
        return info;
    }
}
