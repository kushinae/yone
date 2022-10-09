package org.kushinae.yone.commons.model.configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bnyte
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class GlobalConfiguration {

    /**
     * client cache map
     *  key: data source type code
     *  value: data source client instance
     */
    private ConcurrentHashMap<Class<?>, Object> clientCache = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Class<?>, Object> getClientCache() {
        return clientCache;
    }

    private Boolean enableCamelCase = true;

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
