package org.kushinae.yone.core.client;

import org.kushinae.yone.core.client.proxy.ProxyFactory;
import org.kushinae.yone.core.configuration.GlobalConfiguration;
import org.kushinae.yone.core.enums.EDataSourceType;
import org.kushinae.yone.core.properties.Properties;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class Yone {

    private static GlobalConfiguration configuration;

    /**
     * 获取数据源客户端对象
     * @param dataSourceTypeCode 数据源类型
     * @return 数据源客户端对象
     */
    public static IClient client(Integer dataSourceTypeCode) {
        config();
        IClient IClient = ClientFactory.createClient(EDataSourceType.code(dataSourceTypeCode));
        return new ProxyFactory(IClient, configuration).createInstance();
    }

    /**
     * 直接通过数据源类型和数据源参数构建数据源客户端对象
     * @param dataSourceTypeCode 数据源类型
     * @param properties 数据源客户端参数对象
     * @return 数据源客户端对象
     */
    public static IClient client(Integer dataSourceTypeCode, Properties properties) {
        config();
        // TODO 来吧
        IClient IClient = ClientFactory
                .createClient(EDataSourceType.code(dataSourceTypeCode))
                .build(properties);
        return new ProxyFactory(IClient, configuration).createInstance();
    }

    /**
     * 初始化配置对象
     * @return 配置对象
     */
    public static GlobalConfiguration config() {
        if (Objects.isNull(configuration)) {
            synchronized (Yone.class) {
                if (Objects.isNull(configuration)) {
                    configuration = GlobalConfiguration.getDefaultConfiguration();
                }
            }
        }
        return configuration;
    }

}
