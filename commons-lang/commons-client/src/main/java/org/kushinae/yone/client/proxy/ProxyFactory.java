package org.kushinae.yone.client.proxy;

import org.kushinae.yone.client.IClient;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ProxyFactory<T> {

    private IClient<T> IClientInstance;

    private GlobalConfiguration configuration;

    public ProxyFactory(IClient<T> IClientInstance, GlobalConfiguration configuration) {
        this.IClientInstance = IClientInstance;
        this.configuration = configuration;
    }

    public IClient<T> getClientClass() {
        return IClientInstance;
    }

    public void setClientClass(IClient<T> IClientClass) {
        this.IClientInstance = IClientClass;
    }

    public GlobalConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GlobalConfiguration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings({"unchecked"})
    public IClient<T> createInstance() {
        // 先从缓存中获取确定是否存在
        IClient<T> instance = (IClient<T>) configuration.getClientCache().get(IClientInstance.getClass());
        if (Objects.isNull(instance)) {
            synchronized (configuration.getClientCache()) {
                // 创建客户端代理处理器
                ClientProxyHandler<T> handler = new ClientProxyHandler<>(configuration, this, IClientInstance);
                // 创建代理对象
                instance = (IClient<T>) Proxy.newProxyInstance(IClientInstance.getClass().getClassLoader(), new Class[]{IClient.class, ClientProxy.class}, handler);
                instance.setConfiguration(configuration);
                configuration.getClientCache().put(instance.getClass(), instance);
            }
        }
        return instance;
    }

}
