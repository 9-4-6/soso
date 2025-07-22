package com.gz.soso.pojo.dto;

import com.gz.soso.pojo.common.BasePage;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RoleListDTO extends BasePage implements Serializable {


    @Serial
    private static final long serialVersionUID = 6087744637021611785L;
    private String roleName;

}
