package com.gz.soso.component;

import com.gz.soso.exception.ServiceException;
import com.gz.soso.pojo.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 统一认证服务
 */
@Component
@RequiredArgsConstructor
public class UnifiedAuthService {
    private final AuthenticationManager authenticationManager;

    private final AuthenticationProviderRegistry providerRegistry;

    /**
     * 执行认证
     * @param loginDTO
     * @return
     */
    public Authentication executeAuthenticate(LoginDTO loginDTO) {
        //校验登录类型
        AuthenticationProvider provider = providerRegistry.getProvider(loginDTO.getLoginType());
        if (provider == null) {
            throw new ServiceException(
                    "不支持的登录类型: " + loginDTO.getLoginType() +
                            "，支持的类型: " + providerRegistry.getSupportedLoginTypes());
        }
        // 创建对应类型的AuthenticationToken
        Authentication authToken = createAuthenticationToken(loginDTO);
        // 认证
        return authenticationManager.authenticate(authToken);
    }

    private Authentication createAuthenticationToken(LoginDTO loginDTO) {
        switch (loginDTO.getLoginType()) {
            case 0:
                return new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword());

            default:
                throw new ServiceException("不支持的登录类型");
        }
    }
}
