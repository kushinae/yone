package org.kushinae.yone.client.actuator;

import org.kushinae.yone.commons.model.properties.Properties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Actuator {

    Boolean execute(String script) throws SQLException;

    Connection getConnection();

    Properties getProperties();

}
