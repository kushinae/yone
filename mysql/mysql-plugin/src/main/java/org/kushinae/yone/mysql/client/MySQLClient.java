package org.kushinae.yone.mysql.client;

import org.kushinae.yone.client.AbsRDBClient;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.client.annotation.InterceptorAdvice;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.client.interceptor.PropertiesBuildInterceptor;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

import java.sql.SQLException;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
@InterceptorAdvice(
        {PropertiesBuildInterceptor.class}
)
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
    @SkipInterceptor
    public Client<T> build(Properties properties) {
        this.properties = (MySQLProperties) properties;
        return this;
    }

    @Override
    public Boolean testConnection() throws SQLException {
        return execute(EDataSourceType.code(getDataSourceTypeCode()).getTestConnectionScript());
    }

    @Override
    public Boolean buildComplete() {
        return Objects.nonNull(getProperties());
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        return getActuator().execute(script);
    }


}
