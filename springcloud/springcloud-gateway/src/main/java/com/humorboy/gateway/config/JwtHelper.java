package com.humorboy.gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: CC菜
 * @Date: 2019/9/12 10:52
 * @Remark
 */
@Component
public class JwtHelper {

    @Autowired
    private Algorithm algorithm;

    @Value("${jwt.expireTime:3600}")
    private Long expiration;

    public String sign(String userId, String userName) {
        Date expiresAt = Date.from(Instant.now().plusSeconds(this.expiration));
        String jti = UUID.randomUUID().toString();
        return JWT.create()
                .withIssuer("zshq.com")
                .withJWTId(jti)
                .withAudience(userId, userName)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

}
