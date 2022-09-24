package org.kushinae.yone.client.annotation;

import org.kushinae.yone.client.interceptor.Interceptor;

import java.lang.annotation.*;

/**
 * @author bnyte
 * @since 1.O.O
 */
@Target(ElementType.TYPE)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InterceptorAdvice {

    Class<? extends Interceptor>[] value() default {};

    boolean enable() default true;

    String[] skipMethods() default {};

}
