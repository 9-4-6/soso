package com.gz.soso.pojo.dto;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserAddDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 2784114785365954644L;


    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String mobileNo;


    /**
     * 状态，默认0，0-启用；1-禁用
     */
    private Integer status;
}
