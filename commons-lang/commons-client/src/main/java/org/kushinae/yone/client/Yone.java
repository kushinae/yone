package org.kushinae.yone.client;

import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
// TODO: 2023/3/3 测试todo转issue
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
                    configuration = new GlobalConfiguration();
                }
            }
        }
        return configuration;
    }

}
