package org.kushinae.yone.mysql.client;

import org.junit.jupiter.api.Test;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.facetory.ClientFactory;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;

class MySQLClientTest {

    private MySQLProperties properties;

    {
        properties = new MySQLProperties();
        properties.setIp("127.0.0.1");
        properties.setPort(6033);
        properties.setDatabase("洛云");
    }

    public void before() {

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
    void build() {
        Client<MySQLProperties> client = ClientFactory.createClient(EDataSourceType.MY_SQL, MySQLProperties.class);
        assert client != null;
        MySQLProperties execute = client.build(properties).execute("SELECT * FROM t_user");
        System.out.println(execute);

    }
}