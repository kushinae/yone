package org.kushinae.yone.mysql.client;

import org.kushinae.yone.client.RDClient;
import org.kushinae.yone.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class MySQLClient<T> implements RDClient<T> {

    protected volatile MySQLActuator<T> actuator;

    private MySQLProperties properties;

    @Override
    public Integer getDataSourceTypeCode() {
        return EDataSourceType.MY_SQL.getCode();
    }

    @Override
    public MySQLActuator<T> getActuator() {
        if (actuator == null) {
            synchronized (MySQLClient.class) {
                if (actuator == null) {
                    actuator = new MySQLActuator<>(getProperties());
                }
            }
        }
        return actuator;
    }

    @Override
    public MySQLProperties getProperties() {
        return this.properties;
    }

    @Override
    public T execute(String script) {
        return getActuator().execute(script);
    }


}
