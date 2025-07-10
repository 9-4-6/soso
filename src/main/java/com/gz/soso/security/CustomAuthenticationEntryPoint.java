package com.gz.soso.security;

import com.gz.soso.pojo.common.JsonAuthentication;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.enums.WebResponseEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class CustomAuthenticationEntryPoint extends JsonAuthentication implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("认证失败");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        this.writeJSON(response, WebResponseBuilder.fail(WebResponseEnum.UNAUTHORIZED.getCode(),
                WebResponseEnum.UNAUTHORIZED.getMsg()));
    }
}
