package com.gz.soso.security;

import com.gz.soso.pojo.security.AccessToken;
import com.gz.soso.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtComponent {
    private final JwtProperties jwtProperties;


    /**
     * token 请求参数
     */
    public  static final String ADMIN_AUTH = "soso-auth";

    /**
     * bearer
     */
    public  static final String BEARER = "soso";
    /**
     * bearer的长度
     */
    public  static final Integer MIN_AUTH_LENGTH = 5;


    /**
     * 生成token
     * @param account
     * @return
     */
    public AccessToken doGenerateToken(Long id) {
        //生成token
        final Date now = new Date();
        long millisExpiredTime = now.getTime() + jwtProperties.getTokenExpiration() * 1000;
        final Date expiredTime = new Date(millisExpiredTime);
        String uid = UUID.randomUUID().toString();
        String token = Jwts.builder()
                //token的唯一ID
                .id(uid)
                //用户ID
                .subject(String.valueOf(id))
                .issuedAt(now)
                .expiration(expiredTime)
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
        var jwtTokenInfo = AccessToken.builder()
                .accessToken(token)
                .expiredTime(millisExpiredTime/1000).build();
        return jwtTokenInfo;
    }

    /**
     * 验证token
     * @param token
     */
    public boolean validateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return !isTokenExpired(claims);
    }
    /**
     * 检查是否过期
     */
    private boolean isTokenExpired(Claims claims) {
        return Objects.nonNull(claims) ? claims.getExpiration().before(new Date()):true;
    }

    /**
     * 从token中获取Claims
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)
        );
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            log.error("JWT de-resolve fail, token expired or incorrect, token: {}", token);
        }
        return claims;
    }


    /**
     * 解析token
     * @param request
     * @return
     */
    public  String parseToken(HttpServletRequest request) {
        String adminAuth = request.getHeader(ADMIN_AUTH);
        if (StringUtils.isNotBlank(adminAuth) && adminAuth.length() > MIN_AUTH_LENGTH) {
            String headStr = adminAuth.substring(0, 4).toLowerCase();
            if (headStr.compareTo(BEARER) == 0) {
                adminAuth = adminAuth.substring(MIN_AUTH_LENGTH);
                return adminAuth;
            }
        }
        return null;
    }

}
