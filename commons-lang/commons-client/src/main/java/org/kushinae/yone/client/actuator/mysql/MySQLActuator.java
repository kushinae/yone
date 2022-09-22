package org.kushinae.yone.client.actuator.mysql;

import org.kushinae.yone.client.actuator.RDActuator;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class MySQLActuator<T> implements RDActuator<T> {

    protected volatile Connection connection;

    private MySQLProperties properties;

    public MySQLActuator(MySQLProperties properties) {
        this.properties = properties;
    }

    @Override
    public T execute(String script) {


        return null;
    }

    public Connection getConnection() {
        if (null == connection) {
            synchronized (MySQLActuator.class) {
                if (null == connection) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection("", "", "");
                    } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return connection;
    }

    public MySQLProperties getProperties() {
        return properties;
    }

    public void setProperties(MySQLProperties properties) {
        this.properties = properties;
    }
}
