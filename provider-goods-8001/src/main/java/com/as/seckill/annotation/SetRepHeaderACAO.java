package com.as.seckill.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetRepHeaderACAO {
    String value() default "*";
}
