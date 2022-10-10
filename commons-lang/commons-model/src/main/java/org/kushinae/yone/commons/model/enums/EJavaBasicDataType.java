package org.kushinae.yone.commons.model.enums;

import org.kushinae.yone.commons.model.exception.DataTypeNotFoundException;

import java.util.Date;

/**
 * Java基础数据类型
 * @author bnyte
 * @since 1.0.0
 */
public enum EJavaBasicDataType {

    STRING(String.class, null, EDataTypeTransfer.STRING),
    LONG(Long.class, "long", EDataTypeTransfer.LONG),
    INTEGER(Integer.class, "int", EDataTypeTransfer.INTEGER),
    BYTE(Byte.class, "byte", EDataTypeTransfer.BYTE),
    SHORT(Short.class, "short", EDataTypeTransfer.SHORT),
    BOOLEAN(Boolean.class, "boolean", EDataTypeTransfer.BOOLEAN),
    DATE_UTIL(Date.class, null, EDataTypeTransfer.DATE_UTIL),
    FLOAT(Float.class, "float", EDataTypeTransfer.FLOAT),
    DOUBLE(Double.class, "double", EDataTypeTransfer.DOUBLE),
    ;

    public static boolean contain(Class<?> targetClass) {
        for (EJavaBasicDataType value : values()) {
            if (value.targetClass.equals(targetClass) || targetClass.getName().equals(value.getBaseName()))
                return true;
        }
        return false;
    }

    public static EDataTypeTransfer transfer(Class<?> targetClass) {
        for (EJavaBasicDataType value : values()) {
            if (value.targetClass.equals(targetClass) || targetClass.getName().equals(value.getBaseName()))
                return value.transfer;
        }
        throw new DataTypeNotFoundException();
    }

    private Class<?> targetClass;
    private EDataTypeTransfer transfer;
    private String baseName;

    EJavaBasicDataType(Class<?> targetClass, String baseName, EDataTypeTransfer transfer) {
        this.targetClass = targetClass;
        this.transfer = transfer;
        this.baseName = baseName;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public EDataTypeTransfer getTransfer() {
        return transfer;
    }

    public void setTransfer(EDataTypeTransfer transfer) {
        this.transfer = transfer;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }
}
