package org.kushinae.yone.commons.model.enums;

/**
 * data source definition error code with error message
 * @author bnyte
 * @since 1.0.0
 */
public enum ErrorCode {

    UNKNOWN_EXCEPTION(-1, "%s"),
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
