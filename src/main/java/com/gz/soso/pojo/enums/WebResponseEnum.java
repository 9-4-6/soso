package com.gz.soso.pojo.enums;

import lombok.Getter;

@Getter
public enum WebResponseEnum {
    /**
     * 处理成功
     */
    SUCCESS(200, "处理成功"),

    /**
     * 通用失败（默认业务异常）
     */
    FAIL(500, "处理失败"),

    /**
     * 请求参数无效（客户端错误）
     */
    PARAMETER_INVALID(400, "请求参数错误"),

    /**
     * 未授权（Token 无效或未登录）
     */
    UNAUTHORIZED(401, "未授权访问"),

    /**
     * 禁止访问（无权限）
     */
    FORBIDDEN(403, "无权访问此资源"),

    /**
     * 服务器内部错误（兜底异常）
     */
    INTERNAL_SERVER_ERROR(1000, "服务器内部错误"),

    /**
     * 服务不可用（如熔断降级）
     */
    SERVICE_UNAVAILABLE(1001, "服务不可用"),

    /**
     * 请求超时
     */
    CONNECT_TIMEOUT(1002, "请求超时");

    private int code;
    private String msg;
    WebResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
