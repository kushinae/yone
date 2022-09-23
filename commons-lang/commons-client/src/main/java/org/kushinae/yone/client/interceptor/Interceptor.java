package org.kushinae.yone.client.interceptor;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Interceptor {

    /**
     * 在目标方法执行之前调用该方法
     */
    default boolean before() {
        return true;
    }

    /**
     * 在目标方法执行之前调用该方法
     */
    default void after() {
    }

}
