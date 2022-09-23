package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.facetory.ClientFactory;
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
    }

    @Test
    void execute() {
    }

    @Test
    void build() throws SQLException {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.SQL_SERVER, MySQLProperties.class);
        System.out.println(client.build(properties).execute("insert into user values (null, 1, 'ggboy14', now())"));
    }

    @Test
    void testConnection() throws SQLException {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        System.out.println(client.build(properties).testConnection());
    }
}