package com.envelope.back.enhance.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface Log {

    String value() default "";
}
