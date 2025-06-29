package com.gz.soso.interceptor;


import com.gz.soso.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
/**
 * @author GZ
 * 请求参数拦截器打印
 */
@Component
@Aspect
@Slf4j
public class ParameterInterceptor {

    /**
     * Pointcut
     */
    @Pointcut(value = "execution (* com.gz.soso.controller.*.*(..))")
    private void pointcut() {
    }

    /**
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (log.isInfoEnabled()) {
            Object[] args = proceedingJoinPoint.getArgs();
            if (args.length == 0) {
                return proceedingJoinPoint.proceed();
            }
            Signature sig = proceedingJoinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) sig;
            Object target = proceedingJoinPoint.getTarget();
            Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
            log.info("{}.{} request parameter is: {}",
                    currentMethod.getDeclaringClass().getName(),
                    currentMethod.getName(),
                    isFilter(args[0]) ? StringUtils.EMPTY : JsonUtils.toJson(args[0]));
        }
        return proceedingJoinPoint.proceed();
    }

    /**
     * Filter
     *
     * @param args
     * @return
     */
    private boolean isFilter(Object args) {
        return args instanceof MultipartFile || args instanceof HttpServletRequest || args instanceof HttpServletResponse;
    }

}

