package com.gz.soso.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统岗位表
 * @TableName sys_position
 */
@TableName(value ="sys_position")
@Data
public class SysPosition implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 岗位名称
     */
    @TableField(value = "position_name")
    private String positionName;

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
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除，默认0，0-否；1-是
     */
    @TableField(value = "deleted")
    private Integer deleted;

}