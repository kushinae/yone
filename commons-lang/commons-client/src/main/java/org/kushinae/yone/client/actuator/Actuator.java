package org.kushinae.yone.client.actuator;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Actuator<T> {

    T execute(String script);

}
