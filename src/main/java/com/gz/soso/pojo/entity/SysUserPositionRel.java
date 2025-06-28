package com.gz.soso.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户岗位关联表
 * @TableName sys_user_position_rel
 */
@TableName(value ="sys_user_position_rel")
@Data
public class SysUserPositionRel implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 岗位id
     */
    @TableField(value = "position_id")
    private Long position_id;

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