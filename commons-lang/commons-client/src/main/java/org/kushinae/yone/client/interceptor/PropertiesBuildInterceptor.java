package org.kushinae.yone.client.interceptor;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.commons.model.exception.PropertiesException;
import org.kushinae.yone.commons.model.properties.Properties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class PropertiesBuildInterceptor<T> implements Interceptor<T> {

    @Override
    public boolean before(Client<T> client) {
        Boolean complete = client.buildComplete();
        if (!complete)
            throw new PropertiesException();
        return true;
    }

    @Override
    public void after(Client<T> client) {
        Interceptor.super.after(client);
    }
}
