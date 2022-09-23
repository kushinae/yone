package org.kushinae.yone.commons.model.exception;

import org.kushinae.yone.commons.model.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class PropertiesException extends GlobalException {

    public PropertiesException() {
    }

    public PropertiesException(ErrorCode error) {
        super(error);
    }

    public PropertiesException(String message) {
        error = ErrorCode.PROPERTY_IS_INVALID;
        error.setMessage(String.format(error.getMessage(), message));
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
