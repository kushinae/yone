package org.kushinae.yone.commons.model.exception;

import org.kushinae.yone.commons.model.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class UnSupportedDataSourceException extends GlobalException{

    public UnSupportedDataSourceException() {
    }

    public UnSupportedDataSourceException(ErrorCode error) {
        super(error);
    }

    public UnSupportedDataSourceException(String message) {
        super(message);
    }

    public UnSupportedDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportedDataSourceException(Throwable cause) {
        super(cause);
    }

    public UnSupportedDataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
