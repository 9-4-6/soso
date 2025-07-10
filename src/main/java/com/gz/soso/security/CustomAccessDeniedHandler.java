package com.gz.soso.security;

import com.gz.soso.pojo.common.JsonAuthentication;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.enums.WebResponseEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class CustomAccessDeniedHandler extends JsonAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("Pre-authenticated entry point called. Access denied");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        this.writeJSON(response, WebResponseBuilder.fail(WebResponseEnum.FORBIDDEN.getCode(),WebResponseEnum.FORBIDDEN.getMsg()));
    }
}
