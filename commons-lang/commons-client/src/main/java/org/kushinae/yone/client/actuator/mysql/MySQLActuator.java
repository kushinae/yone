package org.kushinae.yone.client.actuator.mysql;

import org.kushinae.yone.client.actuator.AbsRDBActuator;
import org.kushinae.yone.commons.model.exception.PropertiesException;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class MySQLActuator<T> extends AbsRDBActuator<T> {

    protected volatile Connection connection;

    private MySQLProperties properties;

    public MySQLActuator(MySQLProperties properties) {
        assertMySQLProperties(properties);
        this.properties = properties;
    }

    private void assertMySQLProperties(MySQLProperties properties) {
        if (!StringUtils.hasText(properties.getIp())) {
            throw new PropertiesException("mysql ip cannot be empty");
        }

        if (!StringUtils.hasText(properties.getUsername())) {
            throw new PropertiesException("mysql username cannot be empty");
        }

        if (!StringUtils.hasText(properties.getPassword())) {
            throw new PropertiesException("mysql password cannot be empty");
        }

        if (!StringUtils.hasText(properties.getDatabase())) {
            throw new PropertiesException("mysql database cannot be empty");
        }
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        // execute target sql script
        return statement.execute(script);
    }

    public Connection getConnection() {
        if (null == connection) {
            synchronized (MySQLActuator.class) {
                if (null == connection) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(
                                getJdbcURL(),
                                this.getProperties().getUsername(),
                                this.getProperties().getPassword());
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
                (Objects.isNull(properties.getPort()) ? "3306" : properties.getPort()) +
                "/" +
                this.getProperties().getDatabase();
    }

    public MySQLProperties getProperties() {
        return properties;
    }

    public void setProperties(MySQLProperties properties) {
        this.properties = properties;
    }
}
