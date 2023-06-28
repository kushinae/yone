package org.kushinae.yone.core.client.annotation;

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
