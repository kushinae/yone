package org.kushinae.yone.mysql.client;

import org.kushinae.yone.client.AbsRDBClient;
import org.kushinae.yone.client.Client;
import org.kushinae.yone.client.actuator.mysql.MySQLActuator;
import org.kushinae.yone.client.annotation.InterceptorAdvice;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.client.interceptor.PropertiesBuildInterceptor;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.constant.DefaultSchemaConstant;
import org.kushinae.yone.commons.model.constant.ShowDatabasesConstant;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bnyte
 * @since 1.0.0
 */
@InterceptorAdvice(
        {PropertiesBuildInterceptor.class}
)
public class MySQLClient<T> extends AbsRDBClient<T> {

    protected volatile MySQLActuator<T> actuator;

    private MySQLProperties properties;

    @Override
    @SkipInterceptor
    public Integer getDataSourceTypeCode() {
        return EDataSourceType.MY_SQL.getCode();
    }

    @Override
    public MySQLActuator<T> getActuator() {
        if (actuator == null) {
            synchronized (MySQLClient.class) {
                if (actuator == null) {
                    actuator = new MySQLActuator<>(getProperties());
                }
            }
        }
        return actuator;
    }

    @Override
    public MySQLProperties getProperties() {
        return this.properties;
    }

    @Override
    @SkipInterceptor
    public Client<T> build(Properties properties) {
        this.properties = (MySQLProperties) properties;
        return this;
    }

    @Override
    public Boolean testConnection() throws SQLException {
        return execute(EDataSourceType.code(getDataSourceTypeCode()).getTestConnectionScript());
    }

    @Override
    public Boolean buildComplete() {
        return Objects.nonNull(getProperties());
    }

    protected List<String> databases() throws SQLException {
        List<String> databases = new ArrayList<>();
        Connection connection = getActuator().getConnection();
        ResultSet query = connection.createStatement().executeQuery(ShowDatabasesConstant.MYSQL);
        // if it has next data
        while (query.next()) {
            String schema = query.getString(1);
            databases.add(schema);
        }
        return databases;
    }

    @Override
    public List<String> databases(boolean skipDefault) throws SQLException {
        List<String> databases = databases();
        if (!skipDefault)
            return databases;
        return databases.stream().filter(e -> !DefaultSchemaConstant.MYSQL.contains(e)).collect(Collectors.toList());
    }

    @Override
    public List<String> tables(String database) throws SQLException {
        List<String> tables = new ArrayList<>();
        this.properties.setDatabase(database);
        Connection connection = getActuator().getConnection();
        ResultSet query = connection.createStatement().executeQuery("show tables");
        while (query.next()) {
            String tableName = query.getString(1);
            tables.add(tableName);
        }
        return tables;
    }

    @Override
    public <R> R executeQueryWithSingleResult(String script, Class<R> resultClass) throws Exception {
        Connection connection = getActuator().getConnection();
        ResultSet query = connection.createStatement().executeQuery(script);
        Constructor<R> resultConstructor = resultClass.getConstructor();
        R instance = resultConstructor.newInstance();
        GlobalConfiguration configuration = getConfiguration();

        while (query.next()) {
            for (Field declaredField : resultClass.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(declaredField.getName(), resultClass);
                // TODO: 2022/10/9 应该先if当前属性上的注解然后在if全局配置
                String columnLabel = configuration.getEnableCamelCase() ? StringUtils.lowerCamel2LowerUnderscore(declaredField.getName()) : declaredField.getName();
                Object dbData = null;
                Class<?> fieldType = declaredField.getType();
                dbData = query.getString(columnLabel);
                propertyDescriptor.getWriteMethod().invoke(instance, dbData);
            }
        }
        return instance;
    }

    @Override
    public List<T> executeWithListResult(String script) {
        return null;
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        return getActuator().execute(script);
    }


}
