package org.kushinae.yone.client.actuator;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface RDActuator<T> extends Actuator<T> {

    @Override
    T execute(String script);
}
