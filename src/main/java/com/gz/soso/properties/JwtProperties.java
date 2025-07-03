package com.gz.soso.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("admin.jwt")
@Component
public class JwtProperties {
        /**
         * token 加密key
         */
        private String secretKey = "XOm0XqtRliRZKtFby+OaKg1aNf++vTpJCO3Z38y6eWQ=";

        /**
         * token过期时间 单位秒 默认为1天
         */
        private long tokenExpiration = 86400;
}
