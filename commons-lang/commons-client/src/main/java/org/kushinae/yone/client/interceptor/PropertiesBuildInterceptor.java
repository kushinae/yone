package org.kushinae.yone.client.interceptor;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.commons.model.exception.PropertiesException;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class PropertiesBuildInterceptor<T> implements Interceptor<T> {

    private static final Logger log = LoggerFactory.getLogger(PropertiesBuildInterceptor.class);

    @Override
    public boolean before(Client<T> client, Method method, Object[] args) {
        SkipInterceptor skipInterceptor = method.getAnnotation(SkipInterceptor.class);
        Boolean complete = client.buildComplete();
        if (!complete && ObjectUtils.isNull(skipInterceptor)) {
            log.error("invoke {}#{}({}) error because the method has not executed the build function yet", client.getClass().getName(), method.getName(), Arrays.asList(method.getParameterTypes()).isEmpty() ? "" :  Arrays.asList(method.getParameterTypes()));
            throw new PropertiesException();
        }
        return true;
    }

    @Override
    public void after(Client<T> client) {
        Interceptor.super.after(client);
    }
}
