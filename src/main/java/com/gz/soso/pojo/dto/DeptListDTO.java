package com.gz.soso.pojo.dto;

import com.gz.soso.pojo.common.BasePage;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DeptListDTO extends BasePage implements Serializable {
    @Serial
    private static final long serialVersionUID = 2329056664716477203L;
    private String deptName;

}
