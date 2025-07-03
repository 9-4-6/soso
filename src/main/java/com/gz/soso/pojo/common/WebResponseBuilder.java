package com.gz.soso.pojo.common;


import com.gz.soso.pojo.enums.WebResponseEnum;

/**
 * @author GuoZhong
 */
public class WebResponseBuilder {
    /**
     * 构建处理成功请求
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebResponse success(T data){
        WebResponseEnum success = WebResponseEnum.SUCCESS;
        return WebResponse.builder().success(true).code(success.getCode())
                .msg(success.getMsg()).data(data).build();
    }

    /**
     * 构建处理成功请求
     * @param code 响应代码
     * @param msg 响应消息
     * @param data 响应数据
     * @return
     */
    public static <T> WebResponse success(int code, String msg, T data){
        return WebResponse.builder().success(true).code(code)
                .msg(msg).data(data).build();
    }

    /**
     * 构建处理失败请求
     * @param
     * @param <T>
     * @return
     */
    public static <T> WebResponse fail(){
        WebResponseEnum fail = WebResponseEnum.FAIL;
        return WebResponse.builder().success(false).code(fail.getCode())
                .msg(fail.getMsg()).build();
    }

    /**
     * 构建自定义参数的处理失败请求
     * @param code 响应代码
     * @param msg 响应消息
     * @return
     */
    public static <T> WebResponse fail(int code, String msg){
        return WebResponse.builder().success(false).code(code)
                .msg(msg).build();
    }
}


