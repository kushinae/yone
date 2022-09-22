package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.AbsRDBActuator;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.RDBProperties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbsRDBClient<T> implements Client<T> {

    private RDBProperties properties;

    @Override
    public abstract AbsRDBActuator<T> getActuator();

    @Override
    public RDBProperties getProperties() {
        return this.properties;
    }

}
