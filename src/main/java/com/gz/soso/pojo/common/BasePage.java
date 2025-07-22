package com.gz.soso.pojo.common;

import lombok.Data;

@Data
public class BasePage {
    private Integer current = 1;

    private Integer size = 10;

}
