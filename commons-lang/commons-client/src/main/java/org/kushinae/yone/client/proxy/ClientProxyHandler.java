package org.kushinae.yone.client.proxy;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.annotation.InterceptorAdvice;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.client.interceptor.Interceptor;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.util.CollectionUtils;
import org.kushinae.yone.commons.model.util.MethodHandlersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientProxyHandler<T> implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(ClientProxyHandler.class);

    private Client<T> targetClient;

    private GlobalConfiguration configuration;

    private ProxyFactory<T> proxyFactory;

    private final MethodHandles.Lookup defaultMethodLookup;

    private boolean enableInterceptor = true;

    private boolean endMethodExecution = false;

    private InterceptorAdvice interceptorAdvice;

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
        defaultMethodLookup = MethodHandlersUtils.lookup(targetClient.getClass());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (method.isDefault()) {
            return invokeDefaultMethod(proxy, method, args);
        }

        // 是否跳过拦截器
        if (isSkipInterceptor(method)) {
            return method.invoke(targetClient, args);
        }

        // 执行之前获取所有拦截器
        List<Class<? extends Interceptor>> interceptors = getInterceptors();
        executeInterceptorBefore(interceptors);
        Object invoke = null;

        if (!endMethodExecution) {
            // 执行目标方法
            invoke = method.invoke(targetClient, args);

            // 目标方法执行结果

            // 方法执行之后
            executeInterceptorAfter(interceptors);
        }
        return invoke;
    }

    private void executeInterceptorAfter(List<Class<? extends Interceptor>> interceptors) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<? extends Interceptor> interceptor : interceptors) {
            Constructor<? extends Interceptor> constructor = interceptor.getConstructor();
            Interceptor instance = constructor.newInstance();
            Method before = interceptor.getMethod("after", Client.class);
            before.invoke(instance, targetClient);
        }
    }

    private boolean isSkipInterceptor(Method method) {
        interceptorAdvice = targetClient.getClass().getAnnotation(InterceptorAdvice.class);
        if (Objects.nonNull(interceptorAdvice)) {
            List<String> skipMethods = Arrays.asList(interceptorAdvice.skipMethods());
            if (!CollectionUtils.isEmpty(skipMethods)) {
                boolean contains = skipMethods.contains(method.getName());
                if (!contains) {
                    Method invokeMethod = getCurrentImplInvokeMethod(method);
                    return Objects.nonNull(invokeMethod) && Objects.nonNull(invokeMethod.getAnnotation(SkipInterceptor.class));
                }
            } else {
                Method invokeMethod = getCurrentImplInvokeMethod(method);
                return Objects.nonNull(invokeMethod) && Objects.nonNull(invokeMethod.getAnnotation(SkipInterceptor.class));
            }

        }
        return false;
    }

    private Method getCurrentImplInvokeMethod(Method method) {
        try {
            return targetClient.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private void executeInterceptorBefore(List<Class<? extends Interceptor>> interceptors) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (enableInterceptor && !CollectionUtils.isEmpty(interceptors)) {
            for (Class<? extends Interceptor> interceptor : interceptors) {
                Constructor<? extends Interceptor> constructor = interceptor.getConstructor();
                Interceptor instance = constructor.newInstance();
                Method before = interceptor.getMethod("before", Client.class);
                endMethodExecution = !(boolean) before.invoke(instance, targetClient);
                if (log.isDebugEnabled())
                    if (endMethodExecution)
                        log.debug("The interceptor {}#before() terminated the execution of the target method", instance.getClass().getName());
            }
        }
    }

    private List<Class<? extends Interceptor>> getInterceptors() {
        if (Objects.isNull(interceptorAdvice))
            return null;

        enableInterceptor = interceptorAdvice.enable();

        Class<? extends Interceptor>[] interceptorClasses = interceptorAdvice.value();

        return Arrays.asList(interceptorClasses);
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        return defaultMethodLookup.findSpecial(
                // 从中查询的类或者接口
                targetClient.getClass(),
                // 方法名称
                method.getName(),
                // 方法句柄类型 方法返回值以及方法形参类型列表
                MethodType.methodType(method.getReturnType(), method.getParameterTypes()),
                targetClient.getClass()
        ).bindTo(proxy).invokeWithArguments(args);
    }


}
