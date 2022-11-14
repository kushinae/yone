package org.kushinae.yone.client;

import org.kushinae.yone.client.proxy.ProxyFactory;
import org.kushinae.yone.commons.model.configuration.GlobalConfiguration;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.properties.Properties;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class Yone {

    private static GlobalConfiguration configuration;

    /**
     * 获取数据源客户端对象
     * @param dataSourceTypeCode 数据源类型
     * @return 数据源客户端对象
     */
    public static Client<Object> client(Integer dataSourceTypeCode) {
        config();
        Client client = ClientFactory.createClient(EDataSourceType.code(dataSourceTypeCode));
        return new ProxyFactory<Object>(client, configuration).createInstance();
    }


    /**
     * 获取数据源客户端对象
     *  通过该方式获取到数据源对象需要执行其对应 #build(Properties properties) 否则无法执行数据库相关语句
     *  如果执行则会被拦截器给拦截见下面拦截器介绍
     * @see Client#build(Properties)
     *  所有rdb类型数据源对象都应该通过该方式进行属性对象构建，如果没有执行该方法则视为未初始化数据源参数，则会构建数据源对象失败
     * @see org.kushinae.yone.client.interceptor.PropertiesBuildInterceptor#before(Client, Method, Object[]) 默认拦截器
     *  所有数据源客户端对象应该添加该拦截器以避免不必要异常
     * @param dataSourceTypeCode 数据源类型
     * @param genericType 默认返回结果集对象字节码对象类型
     * @return 数据源客户端对象
     * @param <T> 返回结果集范型类型
     */
    public static <T> Client<T> client(Integer dataSourceTypeCode, Class<T> genericType) {
        config();
        Client<T> client = ClientFactory.createClient(EDataSourceType.code(dataSourceTypeCode), genericType);
        return new ProxyFactory<T>(client, configuration).createInstance();
    }

    /**
     * 直接通过数据源类型和数据源参数构建数据源客户端对象
     * @param dataSourceTypeCode 数据源类型
     * @param properties 数据源客户端参数对象
     * @return 数据源客户端对象
     */
    public static Client<?> client(Integer dataSourceTypeCode, Properties properties) {
        config();
        Client client = ClientFactory
                .createClient(EDataSourceType.code(dataSourceTypeCode))
                .build(properties);
        return new ProxyFactory<Object>(client, configuration).createInstance();
    }

    /**
     * 获取数据源客户端对象
     * @param dataSourceTypeCode 数据源类型
     * @param genericType 默认返回结果集对象字节码对象类型
     * @param properties 数据源客户端参数对象
     * @return 数据源客户端对象
     * @param <T> 返回结果集范型类型
     */
    public static <T> Client<T> client(Integer dataSourceTypeCode, Class<T> genericType, Properties properties) {
        config();
        Client<T> client = ClientFactory
                .createClient(EDataSourceType.code(dataSourceTypeCode), genericType)
                .build(properties);
        return new ProxyFactory<T>(client, configuration).createInstance();
    }

    /**
     * 初始化配置对象
     * @return 配置对象
     */
    public static GlobalConfiguration config() {
        if (Objects.isNull(configuration)) {
            synchronized (Yone.class) {
                if (Objects.isNull(configuration)) {
                    configuration = new GlobalConfiguration();
                }
            }
        }
        return configuration;
    }

}
