package com.gz.soso.pojo.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserListVO implements Serializable {


    @Serial
    private static final long serialVersionUID = 1488869796831854948L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

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
     * 用户类型，默认0，0-普通用户；1-管理员；2-超级管理员
     */
    private Integer type;

    /**
     * 状态，默认0，0-启用；1-禁用
     */
    private Integer status;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
