package com.gz.soso.pojo.dto;

import com.gz.soso.pojo.common.BasePage;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PostListDTO extends BasePage implements Serializable {

    @Serial
    private static final long serialVersionUID = -2982977852036585800L;
    private String positionName;

}
