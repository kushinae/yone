package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.IClient;
import org.kushinae.yone.client.Yone;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

public class MySQLClientTest {

    @Test
    void testDatabases() {
        MySQLProperties properties = new MySQLProperties();
        properties.setIp("127.0.0.1");
        properties.setPassword("123456");
        properties.setUsername("root");
        properties.setPort(3306);
        IClient client = Yone.client(EDataSourceType.MY_SQL.getCode())
                .build(properties);
        System.out.println(client.databases(true));
    }
  
}