package org.kushinae.yone.commons.model.exception;

import org.kushinae.yone.commons.model.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientNotFoundException extends GlobalException {

    public ClientNotFoundException() {
        super(ErrorCode.CLIENT_NOT_FOUND.getMessage());
        error = ErrorCode.CLIENT_NOT_FOUND;
    }

    public ClientNotFoundException(ErrorCode error) {
        super(error);
    }

    public ClientNotFoundException(String message) {
        super(message);
        error = ErrorCode.CLIENT_NOT_FOUND;
        error.setMessage(String.format(error.getMessage(), message));
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
