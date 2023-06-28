package org.kushinae.yone.core.configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bnyte
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class GlobalConfiguration {

    private static final GlobalConfiguration globalConfiguration = new GlobalConfiguration();
    /**
     * client cache map
     *  key: data source type code
     *  value: data source client instance
     */
    private ConcurrentHashMap<Class<?>, Object> clientCache = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Class<?>, Object> getClientCache() {
        return clientCache;
    }

    /**
     * 是否启用驼峰转换
     *  account_type 会映射 实体类 accountType
     */
    private Boolean enableCamelCase = true;

    public static GlobalConfiguration getDefaultConfiguration() {
        return GlobalConfiguration.globalConfiguration;
    }

    public void setClientCache(ConcurrentHashMap<Class<?>, Object> clientCache) {
        this.clientCache = clientCache;
    }

    public Boolean getEnableCamelCase() {
        return enableCamelCase;
    }

    public void setEnableCamelCase(Boolean enableCamelCase) {
        this.enableCamelCase = enableCamelCase;
    }
}
