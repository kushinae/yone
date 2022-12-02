package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.AbsRDBActuator;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.properties.RDBProperties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbsRDBIClient<T> implements IClient<T> {

    private RDBProperties properties;

    private GlobalConfiguration configuration;

    @Override
    public abstract AbsRDBActuator<T> getActuator();

    @SuppressWarnings("unused")
    public void setProperties(RDBProperties properties) {
        this.properties = properties;
    }

    @Override
    public RDBProperties getProperties() {
        return this.properties;
    }

    @Override
    public void setConfiguration(GlobalConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    @SkipInterceptor
    public GlobalConfiguration getConfiguration() {
        return this.configuration;
    }
}
