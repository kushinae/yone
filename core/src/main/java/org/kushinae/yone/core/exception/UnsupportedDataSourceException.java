package org.kushinae.yone.core.exception;

import org.kushinae.yone.core.enums.ErrorCode;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class UnsupportedDataSourceException extends GlobalException{

    public UnsupportedDataSourceException() {
        super(ErrorCode.UNSUPPORTED_DATASOURCE_TYPE);
    }

    public UnsupportedDataSourceException(ErrorCode error) {
        super(error);
    }

    public UnsupportedDataSourceException(String message) {
        super(message);
    }

    public UnsupportedDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedDataSourceException(Throwable cause) {
        super(cause);
    }

    public UnsupportedDataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
