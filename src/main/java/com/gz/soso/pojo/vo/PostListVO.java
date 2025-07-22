package com.gz.soso.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PostListVO implements Serializable {

    private Long id;
    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，默认0，0-启用；1-禁用
     */
    private Integer status;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
