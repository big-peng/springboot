package com.sippr.demo.common.annotaion;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * dao annotation
 * @author hanbd
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Dao {
    String value() default "";
}
