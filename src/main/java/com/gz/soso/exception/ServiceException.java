package com.gz.soso.exception;

public class ServiceException extends RuntimeException{
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
