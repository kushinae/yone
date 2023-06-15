package org.kushinae.yone.client.actuator.mysql;

import org.kushinae.yone.client.actuator.AbstractRDBMSActuator;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;
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
public class MySQLActuator extends AbstractRDBMSActuator {

    public MySQLActuator(MySQLProperties properties) {
        super(properties);
    }

    @Override
    public Connection getConnection() {
        if (null == connection) {
            synchronized (MySQLActuator.class) {
                if (null == connection) {
                    try {
                        Class.forName(StringUtils.hasText(properties.getDrive()) ? properties.getDrive() : EDataSourceType.MY_SQL.getDrive());
                        connection = DriverManager.getConnection(
                                getJdbcURL(),
                                properties.getUsername(),
                                properties.getPassword());
                    } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return connection;
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        // execute target sql script
        return statement.execute(script);
    }



    @Override
    public Properties getProperties() {
        return super.properties;
    }

    private String getJdbcURL() {
        return "jdbc:mysql://" +
                this.properties.getHost() +
                ":" +
                (Objects.isNull(properties.getPort()) ? "3306" : properties.getPort()) +
                (StringUtils.hasText(properties.getDatabase()) ? ("/" +
                properties.getDatabase()) : "");
    }
}
