package com.gz.soso.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统角色表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String role_name;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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