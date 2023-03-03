package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.IClient;
import org.kushinae.yone.client.Yone;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.MethodHandlersUtils;
import org.kushinae.yone.mysql.client.dto.User;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Objects;

class MySQLClientTest {

    private final MySQLProperties properties;

    {
        properties = new MySQLProperties();
        properties.setIp("127.0.0.1");
        properties.setPort(3306);
        properties.setDatabase("yong");
        properties.setUsername("root");
        properties.setPassword("123456");
    }

    @Test
    void getDataSourceTypeCode() {
        IClient<MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class);
        Assertions.assertEquals(IClient.getDataSourceTypeCode(), EDataSourceType.MY_SQL.getCode());
    }

    @Test
    void getActuator() {
        IClient<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        Assertions.assertNotNull(client);
    }

    @Test
    void getProperties() {
        IClient<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        Assertions.assertNotNull(client);
    }

    @Test
    void execute() throws SQLException {
        IClient<MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        assert Objects.nonNull(IClient.execute("select version()"));
    }

    @Test
    void build() throws SQLException {
        // 加载客户端
        IClient<?> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), properties);
        Boolean testConnection = IClient.testConnection();
        System.out.println(testConnection);
    }

    @Test
    void testConnection() throws SQLException {
        IClient<MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        System.out.println(IClient.testConnection());
    }

    @Test
    void testProxy() throws SQLException {
        IClient<Object> IClient = Yone.client(EDataSourceType.MY_SQL.getCode()).build(properties);
        System.out.println(IClient.testConnection());
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
        IClient<? extends MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        IClient.databases(true);
    }

    @Test
    void testTables() throws SQLException {
        IClient<? extends MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        System.out.println(IClient.tables("bnyte"));
    }

    @Test
    void testExecuteQueryWithSingleResult() throws Exception {
        IClient<? extends MySQLProperties> IClient = Yone.client(EDataSourceType.MY_SQL.getCode(), properties.getClass(), properties);
        System.out.println(IClient.executeQueryWithSingleResult("select * from t_user", User.class));
    }
}