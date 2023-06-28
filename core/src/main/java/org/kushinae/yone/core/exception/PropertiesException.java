package org.kushinae.yone.core.exception;

import org.kushinae.yone.core.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class PropertiesException extends GlobalException {

    public PropertiesException() {
        super(ErrorCode.PROPERTY_IS_INVALID.getMessage());
        error = ErrorCode.PROPERTY_IS_INVALID;
        error.setMessage(ErrorCode.PROPERTY_IS_INVALID.getMessage());
    }

    public PropertiesException(ErrorCode error) {
        super(error);
    }

    public PropertiesException(String message) {
        error = ErrorCode.PROPERTY_IS_INVALID;
        error.setMessage(ErrorCode.PROPERTY_IS_INVALID.getMessage());
    }

    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesException(Throwable cause) {
        super(cause);
    }

    public PropertiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
