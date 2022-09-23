package org.kushinae.yone.commons.model.exception;

import org.kushinae.yone.commons.model.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class GlobalException extends RuntimeException {

    /**
     * error info
     */
    protected ErrorCode error = ErrorCode.UNKNOWN_EXCEPTION;

    public GlobalException() {
    }

    public GlobalException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    public GlobalException(String message) {
        super(message);
        error.setMessage(String.format(error.getMessage(), message));
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
