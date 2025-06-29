package com.gz.soso.pojo.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 496048161305780496L;

    private Long id;
    private String accessToken;
    private Long expirationTime;
    private String name;
    private String avatar;
}
