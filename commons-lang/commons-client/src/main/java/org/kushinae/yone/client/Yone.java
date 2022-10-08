package org.kushinae.yone.client;

import org.kushinae.yone.client.facetory.ClientFactory;
import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class Yone {

    private static GlobalConfiguration configuration;

    public static Client<Object> client(Integer dataSourceTypeCode) {
        config();
        Client client = ClientFactory.createClient(EDataSourceType.code(dataSourceTypeCode));
        return new ProxyFactory<Object>(client, configuration).createInstance();
    }

    public static <T> Client<T> client(Integer dataSourceTypeCode, Class<T> genericType) {
        config();
        Client<T> client = ClientFactory.createClient(EDataSourceType.code(dataSourceTypeCode), genericType);
        return new ProxyFactory<T>(client, configuration).createInstance();
    }

    public static Client<?> client(Integer dataSourceTypeCode, Properties properties) {
        config();
        Client client = ClientFactory
                .createClient(EDataSourceType.code(dataSourceTypeCode))
                .build(properties);
        return new ProxyFactory<Object>(client, configuration).createInstance();
    }

    public static <T> Client<T> client(Integer dataSourceTypeCode, Class<T> genericType, Properties properties) {
        config();
        Client<T> client = ClientFactory
                .createClient(EDataSourceType.code(dataSourceTypeCode), genericType)
                .build(properties);
        return new ProxyFactory<T>(client, configuration).createInstance();
    }

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
