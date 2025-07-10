package com.gz.soso.pojo.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6919117078169429888L;
    /**
     * 平台类型 1：iOS，2：Android，3：web
     */
    private Integer platformId;
    /**
     * 登录类型- 0：密码 （1：短信验证码 2 微信授权 等 ）
     *
     * */
    private Integer loginType;
    /**
     * 账号登录
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 预留字段 手机号登录用
     */
    private String mobile;
    /**
     * 预留字段 短信验证码
     */
    private String smsCode;
    /**
     * 预留字段 微信授权码登录
     */
    private String wxCode;
}
