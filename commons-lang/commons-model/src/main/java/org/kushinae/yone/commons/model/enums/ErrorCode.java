package org.kushinae.yone.commons.model.enums;

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

    CLIENT_NOT_FOUND(900000, "data source client not found"),
    UNSUPPORTED_DATASOURCE_TYPE(900001, "unsupported data source type"),
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
