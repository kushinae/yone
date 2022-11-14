package org.kushinae.yone.client.actuator;

import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Actuator<T> {

    Boolean execute(String script) throws SQLException;

    Connection getConnection();

    Properties getProperties();

}
