package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.Actuator;
import org.kushinae.yone.client.actuator.RDActuator;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.RDProperties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface RDClient<T> extends Client<T> {

    @Override
    RDActuator<T> getActuator();

    @Override
    RDProperties getProperties();


}
