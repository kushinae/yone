package org.kushinae.yone.core.enums;

/**
 * 异常code范围规范定义
 *  -1 系统级别异常
 *  100000 - 199999 为参数或相关校验型异常
 *  900000 - 999999 为系统级别异常
 * data source definition error code with error message
 * @author bnyte
 * @since 1.0.0
 */
public enum ErrorCode {

    UNKNOWN_EXCEPTION(-1, "%s"),

    PROPERTY_IS_INVALID(100000, "property object not initialized"),
    DATA_TYPE_MAPPING_ERROR(100001, "data type mapping error"),

    CLIENT_NOT_FOUND(900000, "data source client not found"),
    UNSUPPORTED_DATASOURCE_TYPE(900001, "unsupported data source type"),
    DATA_TYPE_NOT_FOUND(900002, "data type not found"),
    ;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * error message code
     */
    private Integer code;

    /**
     * error message
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
