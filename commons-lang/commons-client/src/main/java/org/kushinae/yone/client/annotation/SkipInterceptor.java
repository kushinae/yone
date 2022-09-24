package org.kushinae.yone.client.annotation;

import org.kushinae.yone.client.interceptor.Interceptor;

import java.lang.annotation.*;

/**
 * @author bnyte
 * @since 1.O.O
 */
@Target(ElementType.METHOD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SkipInterceptor {
}
