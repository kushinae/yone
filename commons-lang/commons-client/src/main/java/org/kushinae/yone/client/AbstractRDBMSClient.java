package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.Actuator;
import org.kushinae.yone.client.annotation.SkipInterceptor;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.constant.DefaultSchemaConstant;
import org.kushinae.yone.commons.model.constant.ShowDatabasesConstant;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.enums.EDataTypeTransfer;
import org.kushinae.yone.commons.model.enums.EJavaBasicDataType;
import org.kushinae.yone.commons.model.properties.Properties;
import org.kushinae.yone.commons.model.properties.mysql.MySQLProperties;
import org.kushinae.yone.commons.model.util.AssertUtils;
import org.kushinae.yone.commons.model.util.ObjectUtils;
import org.kushinae.yone.commons.model.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public abstract class AbstractRDBMSClient implements IClient {

    protected volatile Actuator actuator;

    @Override
    public Connection getConnection() {
        return getActuator().getConnection();
    }

    @Override
    public Boolean testConnection() throws SQLException {
        return execute(EDataSourceType.code(getDataSourceTypeCode()).getTestConnectionScript());
    }

    @Override
    public MySQLProperties getProperties() {
        return (MySQLProperties) getActuator().getProperties();
    }

    @Override
    @SkipInterceptor
    public void buildBeforeAssert(Properties properties) {
        AssertUtils.hasText(properties.getIp(), "Data source host or ip cannot be empty.");
        AssertUtils.hasText(properties.getUsername(), "Data source username cannot be empty.");
        AssertUtils.hasText(properties.getPassword(), "Data source password cannot be empty.");
        AssertUtils.notNull(properties.getPort(), "Data source port cannot be empty.");
    }

    @Override
    public Boolean buildComplete() {
        return ObjectUtils.nonNull(actuator) && ObjectUtils.nonNull(getProperties());
    }

    protected List<String> databases() {
        try {
            List<String> databases = new ArrayList<>();
            Connection connection = getConnection();
            ResultSet query = connection.createStatement().executeQuery(ShowDatabasesConstant.MYSQL);
            // if it has next data
            while (query.next()) {
                String schema = query.getString(1);
                databases.add(schema);
            }
            return databases;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean execute(String script) throws SQLException {
        return getActuator().execute(script);
    }

    @Override
    public List<String> databases(boolean skipDefault) {
        List<String> databases = databases();
        if (!skipDefault)
            return databases;
        return databases.stream().filter(e -> !DefaultSchemaConstant.MYSQL.contains(e)).collect(Collectors.toList());
    }

    @Override
    public List<String> tables(String database) throws SQLException {
        List<String> tables = new ArrayList<>();
        getProperties().setDatabase(database);
        Connection connection = getConnection();
        ResultSet query = connection.createStatement().executeQuery("show tables");
        while (query.next()) {
            String tableName = query.getString(1);
            tables.add(tableName);
        }
        return tables;
    }

    @Override
    public <R> R executeQueryWithSingleResult(String script, Class<R> resultClass) {
        R instance;
        try {
            Connection connection = getConnection();
            ResultSet query = connection.createStatement().executeQuery(script);
            Constructor<R> resultConstructor = resultClass.getConstructor();
            instance = resultConstructor.newInstance();

            while (query.next()) {
                for (Field field : resultClass.getDeclaredFields()) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), resultClass);
                    Object dbData = getColumnDataWithFieldType(field, query);
                    propertyDescriptor.getWriteMethod().invoke(instance, dbData);
                }
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | IntrospectionException e) {
            // 暂时统一处理
            throw new RuntimeException(e);
        }
        return instance;
    }

    protected Object getColumnDataWithFieldType(Field field, ResultSet resultSet) throws SQLException {
        GlobalConfiguration configuration = getConfiguration();
        String columnLabel = configuration.getEnableCamelCase() ? StringUtils.lowerCamel2LowerUnderscore(field.getName()) : field.getName();
        Class<?> fieldType = field.getType();
        if (EDataTypeTransfer.basicDataType(fieldType)) {
            return EJavaBasicDataType.transfer(fieldType).getDataWithFieldType(columnLabel, resultSet);
        }
        return null;
    }

}
