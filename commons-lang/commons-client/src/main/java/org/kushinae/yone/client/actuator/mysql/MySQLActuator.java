package org.kushinae.yone.client.actuator.mysql;

import org.kushinae.yone.client.actuator.AbsRDBActuator;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class MySQLActuator<T> extends AbsRDBActuator<T> {

    protected volatile Connection connection;

    private MySQLProperties properties;

    public MySQLActuator(MySQLProperties properties) {
        this.properties = properties;
    }

    @Override
    public T execute(String script) {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(script);
            return new T();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public Connection getConnection() {
        if (null == connection) {
            synchronized (MySQLActuator.class) {
                if (null == connection) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(getJdbcURL(), "", "");
                    } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return connection;
    }

    private String getJdbcURL() {
        return "jdbc:mysql://" +
                this.properties.getIp() +
                ":" +
                (Objects.isNull(properties.getPort()) ? "80" : properties.getPort());
    }

    public MySQLProperties getProperties() {
        return properties;
    }

    public void setProperties(MySQLProperties properties) {
        this.properties = properties;
    }
}
