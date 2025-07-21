package com.gz.soso.security;

import cn.hutool.core.lang.Assert;
import com.gz.soso.annotation.LoginProvider;
import com.gz.soso.exception.ServiceException;
import com.gz.soso.pojo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * 用户密码登录provider
 */
@Component
@RequiredArgsConstructor
@Slf4j
@LoginProvider(type = 0,description = "密码登录")
public class PasswordAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 账号
        String username = authentication.getName();
        log.info("用户登录-账号名:{}",username);
        // 密码
        String rawPassword = (String) authentication.getCredentials();
        CustomUserDetails userDetails;
        try {
            userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ServiceException("用户名或密码错误");
        }
        Assert.isTrue(Objects.nonNull(userDetails),()->new ServiceException("用户名或密码错误"));
        // 校验密码是否正确
        String md5Password = DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        Assert.isTrue(Objects.equals(userDetails.getPassword(),md5Password),()->new ServiceException("用户名或密码错误"));
        // 校验用户是否被禁用
        Assert.isTrue(Objects.equals(userDetails.getStatus(),0),()->new ServiceException("用户名或密码错误"));
        // 5. 构造已认证的 Authentication 对象
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
