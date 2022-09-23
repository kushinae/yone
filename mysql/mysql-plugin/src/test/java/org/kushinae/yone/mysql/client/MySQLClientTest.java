package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.facetory.ClientFactory;
import org.kushinae.yone.client.proxy.ClientProxyHandler;
import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

import java.sql.SQLException;

class MySQLClientTest {

    private MySQLProperties properties;

    {
        properties = new MySQLProperties();
        properties.setIp("mysql-1.9zdata.cn");
        properties.setPort(3307);
        properties.setDatabase("db_ljx_test");
        properties.setUsername("dmp");
        properties.setPassword("Ioubuy123");
    }

    @Test
    void getDataSourceTypeCode() {

    }

    @Test
    void getActuator() {
    }

    @Test
    void getProperties() {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        client.build(properties);
        System.out.println(client.getProperties());
    }

    @Test
    void execute() throws SQLException {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        System.out.println(client.build(properties).execute("insert into user values (null, 1, 'ggboy14', now())"));
    }

    @Test
    void build() {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        System.out.println(client.build(properties));
    }

    @Test
    void testConnection() throws SQLException {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        System.out.println(client.build(properties).testConnection());
    }

    @Test
    void testProxy() throws SQLException {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        client.build(properties);
        GlobalConfiguration configuration = new GlobalConfiguration();
        ProxyFactory<MySQLProperties> factory = new ProxyFactory<>(client, configuration);
        Client<MySQLProperties> instance = factory.createInstance();
        System.out.println("✅最后获取执行的结果通过返回值获取：" + instance.testConnection());
    }
}