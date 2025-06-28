package com.gz.soso.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 手机号
     */
    @TableField(value = "mobile_no")
    private String mobile_no;

    /**
     * 用户类型，默认0，0-普通用户；1-管理员；2-超级管理员
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 状态，默认0，0-启用；1-禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Long create_user;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private Long update_user;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 是否删除，默认0，0-否；1-是
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}