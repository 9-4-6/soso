package com.gz.soso.security;

import com.gz.soso.pojo.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private JwtComponent jwtComponent;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // 1. 从请求头获取 Token（通常格式：Authorization: Bearer <token>）
        String token = jwtComponent.parseToken(request);
        if (StringUtils.isEmpty(token)) {
            log.info("Token is empty or not start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }
        if (!jwtComponent.validateToken(token)) {
            log.info("Token is invalid");
            filterChain.doFilter(request, response);
            return;
        }
        //认证信息存入 SecurityContext
        Claims claimsFromToken = jwtComponent.getClaimsFromToken(token);
        if(Objects.isNull(claimsFromToken)){
            log.info("Token is invalid");
            filterChain.doFilter(request, response);
            return;
        }
        String id = claimsFromToken.getSubject();
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(Long.valueOf(id));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                customUserDetails,
                null,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 5. 继续执行后续过滤器
        filterChain.doFilter(request, response);
    }
}
