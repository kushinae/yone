package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.Yone;
import org.kushinae.yone.client.actuator.Actuator;
import org.kushinae.yone.client.facetory.ClientFactory;
import org.kushinae.yone.client.proxy.ClientProxyHandler;
import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.MethodHandlersUtils;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Objects;

class MySQLClientTest {

    private static final GlobalConfiguration config = new GlobalConfiguration();

    private MySQLProperties properties;

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
    void build() {
        // 加载客户端
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        // 构建客户端相关属性
        client.build(properties);

        // 创建全局配置对象
        GlobalConfiguration configuration = new GlobalConfiguration();

        // 创建代理工厂
        ProxyFactory<MySQLProperties> factory = new ProxyFactory<>(client, configuration);

        // 通过代理工厂获取客户端的代理对象
        Client<MySQLProperties> instance = factory.createInstance();
        System.out.println("✅最后获取执行的结果通过返回值获取：" + instance.build(properties));
    }

    @Test
    void testConnection() throws SQLException {
        Client<MySQLProperties> client = Yone.client(EDataSourceType.MY_SQL.getCode(), MySQLProperties.class, properties);
        System.out.println(client.testConnection());
    }

    @Test
    void testProxy() throws SQLException {
        // 加载客户端
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        // 构建客户端相关属性
        client.build(properties);

        // 创建全局配置对象
        GlobalConfiguration configuration = new GlobalConfiguration();

        // 创建代理工厂
        ProxyFactory<MySQLProperties> factory = new ProxyFactory<>(client, configuration);

        // 通过代理工厂获取客户端的代理对象
        Client<MySQLProperties> instance = factory.createInstance();
        System.out.println("✅最后获取执行的结果通过返回值获取：" + instance.testConnection());
    }

    @Test
    void testMethodHandler() {
        MethodHandles.Lookup lookup = MethodHandlersUtils.lookup(MySQLClientTest.class);
        System.out.println(lookup);
    }

    @Test
    void testToString() {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        client.build(properties);
        GlobalConfiguration configuration = new GlobalConfiguration();
        ProxyFactory<MySQLProperties> factory = new ProxyFactory<>(client, configuration);
        Client<MySQLProperties> instance = factory.createInstance();
        System.out.println(instance.toString());
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
}