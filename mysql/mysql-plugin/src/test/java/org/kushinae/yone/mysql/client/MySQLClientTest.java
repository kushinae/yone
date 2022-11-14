package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.Yone;
import org.kushinae.yone.client.ClientFactory;
import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.MethodHandlersUtils;
import org.kushinae.yone.mysql.client.dto.User;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

class MySQLClientTest {

    private final MySQLProperties properties;

    {
        properties = new MySQLProperties();
        properties.setIp("121.40.209.124");
        properties.setPort(6033);
        properties.setDatabase("bnyte");
        properties.setUsername("root");
        properties.setPassword("5c2891d9-45fb-4240-9f0e-50222099d9bd");
    }

    @Test
    void getDataSourceTypeCode() {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class);
        assert client.getDataSourceTypeCode().equals(EDataSourceType.MY_SQL.getCode());
    }

    @Test
    void getActuator() {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        assert Objects.nonNull(client.getActuator());
    }

    @Test
    void getProperties() {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        assert Objects.nonNull(client);
    }

    @Test
    void execute() throws SQLException {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        assert Objects.nonNull(client.execute("select version()"));
    }

    @Test
    void build() throws SQLException {
        // 加载客户端
        Client<?> client = Yone.client(EDataSourceType.MY_SQL.getCode(), properties);
        Boolean testConnection = client.testConnection();
        System.out.println(testConnection);
    }

    @Test
    void testConnection() throws SQLException {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        System.out.println(client.testConnection());
    }

    @Test
    void testProxy() throws SQLException {
        Client<Object> client = Yone.client(EDataSourceType.MY_SQL.getCode()).build(properties);
        System.out.println(client.testConnection());
    }

    @Test
    void testMethodHandler() {
        MethodHandles.Lookup lookup = MethodHandlersUtils.lookup(MySQLClientTest.class);
        System.out.println(lookup);
    }

    @Test
    void testToString() {
    }

    @Test
    void testDatabases() throws SQLException {
        Client<? extends MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        client.databases(true);
    }

    @Test
    void testTables() throws SQLException {
        Client<? extends MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        System.out.println(client.tables("bnyte"));
    }

    @Test
    void testExecuteQueryWithSingleResult() throws Exception {
        Client<? extends MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        System.out.println(client.executeQueryWithSingleResult("select * from t_user", User.class));
    }
}