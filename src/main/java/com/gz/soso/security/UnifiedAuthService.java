package com.gz.soso.security;

import com.gz.soso.exception.ServiceException;
import com.gz.soso.pojo.security.AccessToken;
import com.gz.soso.pojo.dto.LoginDTO;
import com.gz.soso.pojo.security.CustomUserDetails;
import com.gz.soso.pojo.vo.LoginVO;
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

    private final JwtComponent jwtComponent;




    public LoginVO login(LoginDTO loginDTO) {
        // 执行认证
        Authentication authentication = executeAuthenticate(loginDTO);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        // 生成令牌
        AccessToken accessToken = jwtComponent.doGenerateToken(customUserDetails.getId());
        // todo 存储redis key:userId + platformId + uuid  value:用户信息
        return LoginVO.builder()
                .id(customUserDetails.getId())
                .name(customUserDetails.getName())
                .accessToken(accessToken.getAccessToken())
                .expirationTime(accessToken.getExpiredTime())
                .build();

    }
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
