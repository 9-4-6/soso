package com.gz.soso.pojo.enums;

import lombok.Getter;

@Getter
public enum WebResponseEnum {
    /**
     *  处理成功
     */
    SUCCESS("S00000","处理成功"),
    /**
     *  处理失败
     */
    FAIL("E00001","处理失败"),

    /**
     * 请求参数错误
     */
    PARAMETER_INVALID("E00400", "请求参数错误"),

    /**
     * 业务服务异常统一编码
     */
    INTERNAL_SERVER_ERROR("E00010", "服务器内部错误"),

    /**
     * 服务不可用错误编码
     */
    SERVICE_UNAVAILABLE("E00011", "服务不可用"),

    /**
     * 未知异常
     */
    UNKNOWN("E00012", "未知异常!"),
    /**
     * 请求超时
     */
    CONNECT_TIMEOUT("E00013", "请求超时");

    private String code;
    private String msg;
    WebResponseEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
