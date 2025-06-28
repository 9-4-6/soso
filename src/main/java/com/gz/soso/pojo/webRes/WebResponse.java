package com.gz.soso.pojo.webRes;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GuoZhong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebResponse<T> {
    /**
     * 响应码
     */
    @JsonProperty
    private String code;
    /**
     * 响应消息
     */
    @JsonProperty
    private String msg;

    /**
     * 是否处理成功
     */
    @JsonProperty
    private Boolean success = true;

    /**
     * 响应数据
     */
    @JsonProperty
    private T data;

}


