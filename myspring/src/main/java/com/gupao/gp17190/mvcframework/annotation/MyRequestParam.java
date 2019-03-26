package com.gupao.gp17190.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/26
 * @description
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
