package org.kushinae.yone.commons.model.exception;

import org.kushinae.yone.commons.model.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class DataTypeNotFoundException extends GlobalException {

    public DataTypeNotFoundException() {
        super(ErrorCode.DATA_TYPE_NOT_FOUND.getMessage());
        error = ErrorCode.DATA_TYPE_NOT_FOUND;
    }

    public DataTypeNotFoundException(ErrorCode error) {
        super(error);
    }

    public DataTypeNotFoundException(String message) {
        super(message);
    }

    public DataTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
