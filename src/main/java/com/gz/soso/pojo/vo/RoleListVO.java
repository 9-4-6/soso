package com.gz.soso.pojo.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RoleListVO implements Serializable {


    @Serial
    private static final long serialVersionUID = -1982306719159443754L;
    private Long id;
    /**
     * 角色名称
     */
    private String RoleName;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，默认0，0-启用；1-禁用
     */
    private Integer status;


    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
