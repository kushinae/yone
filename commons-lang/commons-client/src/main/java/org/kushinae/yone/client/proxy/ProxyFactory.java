package org.kushinae.yone.client.proxy;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ProxyFactory<T> {

    private Client<T> clientInstance;

    private GlobalConfiguration configuration;

    public ProxyFactory(Client<T> clientInstance, GlobalConfiguration configuration) {
        this.clientInstance = clientInstance;
        this.configuration = configuration;
    }

    public Client<T> getClientClass() {
        return clientInstance;
    }

    public void setClientClass(Client<T> clientClass) {
        this.clientInstance = clientClass;
    }

    public GlobalConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GlobalConfiguration configuration) {
        this.configuration = configuration;
    }

    public Client<T> createInstance() {
        // 先从缓存中获取确定是否存在
        Client<T> instance = (Client<T>) configuration.getClientCache().get(clientInstance.getClass());
        if (Objects.isNull(instance)) {
            synchronized (configuration.getClientCache()) {
                if (Objects.isNull(instance)) {
                    // 创建客户端代理处理器
                    ClientProxyHandler<T> handler = new ClientProxyHandler<>(configuration, this, clientInstance);
                    // 创建代理对象
                    instance = (Client<T>) Proxy.newProxyInstance(clientInstance.getClass().getClassLoader(), new Class[]{Client.class, ClientProxy.class}, handler);
                }
            }
        }
        return instance;
    }
}
