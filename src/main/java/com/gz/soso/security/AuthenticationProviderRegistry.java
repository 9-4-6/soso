package com.gz.soso.security;

import com.gz.soso.Annotation.LoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 各种登录方式对应provider注册器
 */
@Component
public class AuthenticationProviderRegistry {
    private final Map<Integer, AuthenticationProvider> providerMap;
    private final List<LoginTypeInfo> loginTypeInfos;

    @Autowired
    public AuthenticationProviderRegistry(List<AuthenticationProvider> providers) {
        // 创建不可变Map
        Map<Integer, AuthenticationProvider> tempMap = new HashMap<>();
        List<LoginTypeInfo> tempInfos = new ArrayList<>();

        for (AuthenticationProvider provider : providers) {
            LoginProvider annotation = provider.getClass().getAnnotation(LoginProvider.class);
            if (annotation != null) {
                tempMap.put(annotation.type(), provider);
                tempInfos.add(new LoginTypeInfo(
                        annotation.type(),
                        annotation.description()
                ));
            }
        }

        this.providerMap = Collections.unmodifiableMap(tempMap);
        this.loginTypeInfos = Collections.unmodifiableList(tempInfos);
    }

    public AuthenticationProvider getProvider(int loginType) {
        return providerMap.get(loginType);
    }

    public List<LoginTypeInfo> getSupportedLoginTypes() {
        return loginTypeInfos;
    }

    public record LoginTypeInfo(int type, String description) {}
}
