package com.humorboy.practise.annotation;

import java.lang.annotation.*;

/**
 * Created by root on 17-5-31.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String value() default "";
}
