package com.gz.soso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] WHITELIST = {
            "/auth/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //加入白名单
                        .requestMatchers(WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                // 禁用默认表单登录
                .formLogin(form -> form.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    /**
     * 暴露AuthenticationManager bean
     * @param providers
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(
            List<AuthenticationProvider> providers) {
        return new ProviderManager(providers);
    }
}
