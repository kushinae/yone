package org.kushinae.yone.core.client.proxy;

import org.kushinae.yone.core.client.IClient;
import org.kushinae.yone.core.configuration.GlobalConfiguration;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ProxyFactory {

    private IClient IClientInstance;

    private GlobalConfiguration configuration;

    public ProxyFactory(IClient IClientInstance, GlobalConfiguration configuration) {
        this.IClientInstance = IClientInstance;
        this.configuration = configuration;
    }

    public IClient getClientClass() {
        return IClientInstance;
    }

    public void setClientClass(IClient IClientClass) {
        this.IClientInstance = IClientClass;
    }

    public GlobalConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GlobalConfiguration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings({"unchecked"})
    public IClient createInstance() {
        // 先从缓存中获取确定是否存在
        IClient instance = (IClient) configuration.getClientCache().get(IClientInstance.getClass());
        if (Objects.isNull(instance)) {
            synchronized (configuration.getClientCache()) {
                // 创建客户端代理处理器
                ClientProxyHandler handler = new ClientProxyHandler(configuration, this, IClientInstance);
                // 创建代理对象
                instance = (IClient) Proxy.newProxyInstance(IClientInstance.getClass().getClassLoader(), new Class[]{IClient.class, ClientProxy.class}, handler);
                instance.setConfiguration(configuration);
                configuration.getClientCache().put(instance.getClass(), instance);
            }
        }
        return instance;
    }

}
