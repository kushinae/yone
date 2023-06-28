package org.kushinae.yone.core.client.actuator;

import org.kushinae.yone.core.properties.mysql.MySQLProperties;

import java.sql.Connection;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbstractRDBMSActuator implements Actuator {

    protected volatile Connection connection;

    protected MySQLProperties properties;

    public AbstractRDBMSActuator(MySQLProperties properties) {
        this.properties = properties;
    }
}
