package org.kushinae.yone.client.interceptor;

import org.kushinae.yone.client.Client;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Interceptor<T> {

    /**
     * 在目标方法执行之前调用该方法
     */
    default boolean before(Client<T> client) {
        return true;
    }

    /**
     * 在目标方法执行之前调用该方法
     */
    default void after() {
    }

}
