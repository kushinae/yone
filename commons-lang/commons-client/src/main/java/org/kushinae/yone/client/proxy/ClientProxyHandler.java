package org.kushinae.yone.client.proxy;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientProxyHandler<T> implements InvocationHandler {

    private Client<T> targetClient;

    private GlobalConfiguration configuration;

    private ProxyFactory<T> proxyFactory;

    private final MethodHandles.Lookup defaultMethodLookup;

    public Client<T> getTargetClient() {
        return targetClient;
    }

    public void setTargetClient(Client<T> targetClient) {
        this.targetClient = targetClient;
    }

    public GlobalConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GlobalConfiguration configuration) {
        this.configuration = configuration;
    }

    public ProxyFactory<T> getProxyFactory() {
        return proxyFactory;
    }

    public void setProxyFactory(ProxyFactory<T> proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    public ClientProxyHandler(GlobalConfiguration configuration, ProxyFactory<T> proxyFactory, Client<T> targetClient) {
        this.configuration = configuration;
        this.proxyFactory = proxyFactory;
        this.targetClient = targetClient;
        defaultMethodLookup = MethodHandles.lookup();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (method.isDefault()) {
            return invokeDefaultMethod(proxy, method, args);
        }

        Object invoke = method.invoke(targetClient, args);
        System.out.println(invoke);
        System.out.println("after...");
        return invoke;
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) {
        return null;
    }


}
