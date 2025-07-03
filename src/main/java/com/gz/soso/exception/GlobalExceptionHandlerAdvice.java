package com.gz.soso.exception;
import com.gz.soso.pojo.enums.WebResponseEnum;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;

/**
 * @author GZ
 * 全局异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("method argument not valid exception:", e);
        ObjectError objectError = e.getBindingResult().getFieldError();
        return WebResponseBuilder.fail(WebResponseEnum.PARAMETER_INVALID.getCode(), objectError.getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public WebResponse illegalArgumentException(IllegalArgumentException e) {
        log.error("illegal argument exception:", e);
        return WebResponseBuilder.fail(WebResponseEnum.PARAMETER_INVALID.getCode(), e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public WebResponse socketTimeoutException(SocketTimeoutException e) {
        log.error("socket timeout exception:", e);
        return WebResponseBuilder.fail(WebResponseEnum.CONNECT_TIMEOUT.getCode(), WebResponseEnum.CONNECT_TIMEOUT.getMsg());
    }

    @ExceptionHandler(ServiceException.class)
    public WebResponse serviceException(ServiceException e) {
        log.error("service exception:", e);
        return WebResponseBuilder.fail(WebResponseEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public WebResponse exception(Exception e) {
        log.error("system error:", e);
        return WebResponseBuilder.fail(WebResponseEnum.INTERNAL_SERVER_ERROR.getCode(), WebResponseEnum.INTERNAL_SERVER_ERROR.getMsg());
    }

}

