package org.kushinae.yone.client.interceptor;

import org.kushinae.yone.client.Client;

import java.lang.reflect.Method;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Interceptor<T> {

    /**
     * 在目标方法执行之前调用该方法
     * @param client 数据源客户端代理对象
     * @param method 当前执行方法
     * @param args 当前方法的行参实例对象
     * @return true则表示允许继续执行 false则表示不允许继续往下执行 默认true
     */
    default boolean before(Client<T> client, Method method, Object[] args) {
        return true;
    }

    /**
     * 在目标方法执行之前调用该方法
     * @param client 数据源客户端代理对象
     */
    default void after(Client<T> client) {
    }

}
