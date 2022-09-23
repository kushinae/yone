package org.kushinae.yone.mysql.client;

import org.kushinae.yone.client.AbsRDBClient;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class MySQLClient<T> extends AbsRDBClient<T> {

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
    public Client<T> build(Properties properties) {
        this.properties = (MySQLProperties) properties;
        return this;
    }

    @Override
    public Boolean testConnection() throws SQLException {
        return execute(EDataSourceType.code(getDataSourceTypeCode()).getTestConnectionScript());
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        return getActuator().execute(script);
    }


}
