package com.fz6m.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 生成和验证jwt的工具类
 */
@Component
@ConfigurationProperties(prefix = "custom.jwt.config")
@Data
public class JwtUtil {

    // 密钥
    private String secretKey;

    //单位秒，默认7天
    private long expires = 60*60*24*7;

    /**
     * 生成JWT
     */
    public String createJWT(String id, String subject, Boolean isLogin) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .claim("isLogin", isLogin);
        if (expires > 0) {
            // expires乘以1000是毫秒转秒
            builder.setExpiration(new Date(nowMillis + expires*1000));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     */
    public Claims parseJWT(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    }


}
