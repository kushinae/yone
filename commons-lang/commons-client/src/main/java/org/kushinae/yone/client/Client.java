package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.Actuator;
import org.kushinae.yone.commons.model.properties.Properties;

import java.sql.SQLException;

/**
 * base data source client
 * @author bnyte
 * @since 1.0.0
 */
public interface Client<T> {

    /**
     * get current data source type code
     * @return {@link org.kushinae.yone.commons.model.enums.EDataSourceType} a code field
     */
    Integer getDataSourceTypeCode();

    /**
     * get current data source actuator
     * @return actuator can execute target sql
     */
    Actuator<T> getActuator();

    Boolean execute(String script) throws SQLException;

    Properties getProperties();

    Client<T> build(Properties properties);

    Boolean testConnection() throws SQLException;

    Boolean buildComplete();

}
