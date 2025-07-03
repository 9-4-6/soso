package com.gz.soso.pojo.security;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class AccessToken implements Serializable {
    @Serial
    private static final long serialVersionUID = 1702017212420638798L;

    private String accessToken;
    private Long expiredTime;
}
