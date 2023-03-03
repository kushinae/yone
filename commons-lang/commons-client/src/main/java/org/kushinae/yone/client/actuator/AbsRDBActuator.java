package org.kushinae.yone.client.actuator;

import org.kushinae.yone.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbsRDBActuator implements Actuator {

    protected volatile Connection connection;

    protected MySQLProperties properties;

    public AbsRDBActuator(MySQLProperties properties) {
        this.properties = properties;
    }
}
