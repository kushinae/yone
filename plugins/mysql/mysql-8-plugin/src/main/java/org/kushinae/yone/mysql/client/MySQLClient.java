package org.kushinae.yone.mysql.client;

import org.kushinae.yone.core.client.AbstractRDBMSClient;
import org.kushinae.yone.core.client.IClient;
import org.kushinae.yone.core.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.core.client.annotation.InterceptorAdvice;
import org.kushinae.yone.core.client.annotation.SkipInterceptor;
import org.kushinae.yone.core.client.interceptor.PropertiesBuildInterceptor;
import org.kushinae.yone.core.enums.EDataSourceType;
import org.kushinae.yone.core.properties.Properties;
import org.kushinae.yone.core.properties.mysql.MySQLProperties;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@InterceptorAdvice(
        {PropertiesBuildInterceptor.class}
)
public class MySQLClient extends AbstractRDBMSClient {

    @Override
    @SkipInterceptor
    public Integer getDataSourceTypeCode() {
        return EDataSourceType.MY_SQL.getCode();
    }

    @Override
    public MySQLActuator getActuator() {
        if (super.actuator == null) {
            synchronized (MySQLClient.class) {
                if (super.actuator == null) {
                    MySQLActuator actuator1 = new MySQLActuator(getProperties());
                    super.actuator = actuator1;
                    return actuator1;
                }
            }
        }
        return (MySQLActuator) super.actuator;
    }

    @Override
    @SkipInterceptor
    public IClient build(Properties properties) {
        MySQLProperties mysqlProperties = (MySQLProperties) properties;
        buildBeforeAssert(properties);
        actuator = new MySQLActuator(mysqlProperties);
        return this;
    }

    @Override
    public <R> List<R> executeWithListResult(String script, Class<R> resultClass) {
        return null;
    }

}
