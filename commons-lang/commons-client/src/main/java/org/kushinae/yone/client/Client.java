package org.kushinae.yone.client;

import org.kushinae.yone.client.actuator.Actuator;
import org.kushinae.yone.commons.model.properties.Properties;

import java.sql.SQLException;

/**
 * base data source client
 * @author bnyte
 * @since 1.0.0
 */
public interface Client<T> {

    /**
     * get current data source type code
     * @return {@link org.kushinae.yone.commons.model.enums.EDataSourceType} a code field
     */
    Integer getDataSourceTypeCode();

    /**
     * get current data source actuator
     * @return actuator can execute target sql
     */
    Actuator<T> getActuator();

    /**
     * 执行脚本
     * @param script 执行自定义脚本
     * @return 返回当前脚本是否执行成功
     * @throws SQLException 执行失败异常
     */
    Boolean execute(String script) throws SQLException;

    /**
     * 获取当前客户端的属性对象 当build之后才会有值
     * @return 数据源客户端属性对象
     */
    Properties getProperties();

    /**
     * 构建属性给当前客户端 必须执行如果没有执行该方法后续执行其他数据源操作会抛出异常
     * @param properties 属性对象 通过自定义构建进行指定，不同的数据源客户端使用的属性对象也不同
     * @return 返回当前构建完成之后的属性对象 可以不适用 该客户端是通过this进行赋值所以返回的对象和this是同一个对象
     */
    Client<T> build(Properties properties);

    /**
     * 测试当前客户端与数据源目标的连通性状态
     * @return 返回是否连接成功 true：成功 false：失败
     * @throws SQLException sql执行异常
     */
    Boolean testConnection() throws SQLException;

    /**
     * 判断当前客户端对象是否已经构建数据源属性成功
     * @return 如果已经构建即执行
     *  即为执行{@linkplain org.kushinae.yone.client.Client#build(Properties)} }
     *  之后且需要其对应的属性赋值成功返回true 否则返回false
     */
    Boolean buildComplete();

}
