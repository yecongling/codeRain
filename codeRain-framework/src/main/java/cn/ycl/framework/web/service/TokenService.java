package cn.ycl.framework.web.service;

import cn.ycl.common.constant.Constants;
import cn.ycl.common.core.domain.entity.SysUser;
import cn.ycl.common.utils.uuid.IdUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌生成
 */

@Component
public class TokenService {
    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    /**
     * 创建令牌
     *
     * @param user 用户信息
     * @return 令牌
     */
    public String createToken(SysUser user){
        String token = IdUtils.fastUUID();
        user.setToken(token);

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}
