package com.gz.soso.security;

import com.gz.soso.exception.ServiceException;
import com.gz.soso.pojo.enums.WebResponseEnum;
import com.gz.soso.pojo.security.CustomUserDetails;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
public class SecurityUtils {
    /**
     * 获取当前认证信息
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户详情（CustomUserDetails）
     * @throws IllegalStateException 如果用户未登录或类型不匹配
     */
    public static CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ServiceException("用户未登录或认证已失效");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        } else {
            throw new ServiceException("当前用户类型不匹配，预期: CustomUserDetails，实际: " + principal.getClass().getName());
        }
    }

    /**
     * 获取当前用户ID（快捷方法）
     */
    public static Long getCurrentUserId() {
        return getCurrentUserDetails().getId();
    }

    /**
     * 获取当前账号
     */
    public static String getCurrentAccount() {
        return getCurrentUserDetails().getUsername();
    }
    /**
     * 获取当前用户名
     */
    public static String getCurrentName() {
        return getCurrentUserDetails().getName();
    }
    /**
     * 认证失败
     *
     * @param response
     * @return
     */
    public static boolean unAuth(HttpServletResponse response, HttpServletRequest request) {
        WebResponse webResponse = WebResponseBuilder.fail(WebResponseEnum.UNAUTHORIZED.getCode(),WebResponseEnum.UNAUTHORIZED.getMsg());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().write(Objects.requireNonNull(JsonUtils.toJson(webResponse)));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return false;
    }
}
