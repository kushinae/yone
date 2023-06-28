package org.kushinae.yone.core.enums;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 数据库数据类型与Java数据类型转换
 * @author bnyte
 * @since 1.0.0
 */
public enum EDataTypeTransfer {

    STRING() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getString(columnLabel);
        }
    },

    LONG() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getLong(columnLabel);
        }
    },

    INTEGER() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getInt(columnLabel);
        }
    },

    BYTE() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getByte(columnLabel);
        }
    },

    SHORT() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getShort(columnLabel);
        }
    },

    BOOLEAN() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getBoolean(columnLabel);
        }
    },

    FLOAT() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getFloat(columnLabel);
        }
    },

    DATE_UTIL() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            Timestamp timestamp = resultSet.getTimestamp(columnLabel);
            if (Objects.isNull(timestamp))
                return null;
            return new Date(resultSet.getTimestamp(columnLabel).getTime());
        }
    },

    DOUBLE() {
        @Override
        public Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException {
            return resultSet.getDouble(columnLabel);
        }
    },
    ;

    /**
     * 是否为Java默认数据类型
     * @param fieldClass 属性的类型class对象
     * @return true则表示是Java基础数据类型，false则表示特殊数据类型
     */
    public static boolean basicDataType(Class<?> fieldClass) {
        return EJavaBasicDataType.contain(fieldClass);
    }

    public abstract Object getDataWithFieldType(String columnLabel, ResultSet resultSet) throws SQLException;

}
