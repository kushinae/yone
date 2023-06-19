package org.kushinae.yone.commons.model.enums;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public enum DataType {
    BIT(Boolean.class),
    TINYINT(Byte.class),
    SMALLINT(Short.class),
    MEDIUMINT(Integer.class),
    INT(Integer.class),
    BIGINT(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    DECIMAL(BigDecimal.class),
    DATE(Date.class),
    TIME(Time.class),
    DATETIME(Timestamp.class),
    TIMESTAMP(Timestamp.class),
    YEAR(Integer.class),
    CHAR(String.class),
    VARCHAR(String.class),
    BINARY(Blob.class),
    VARBINARY(Blob.class),
    TINYBLOB(Blob.class),
    BLOB(Blob.class),
    MEDIUMBLOB(Blob.class),
    LONGBLOB(Blob.class),
    TINYTEXT(String.class),
    TEXT(String.class),
    MEDIUMTEXT(String.class),
    LONGTEXT(String.class),
    ENUM(String.class),
    SET(String.class)
    ;

    private Class<?> javaType;

    DataType(Class<?> javaType) {
        this.javaType = javaType;
    }
}
