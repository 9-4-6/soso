package com.gz.soso.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DeptAddDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6988703135967248425L;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 状态，默认0，0-启用；1-禁用
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
}
