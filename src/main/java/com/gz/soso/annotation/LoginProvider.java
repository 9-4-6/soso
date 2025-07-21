package com.gz.soso.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LoginProvider {
    /**
     * Unique identifier for login type
     * e.g. 0 = password, 1 = SMS, 2 = WeChat
     */
    int type();

    /**
     * Human-readable description of login method
     */
    String description() default "";
}
