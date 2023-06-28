package org.kushinae.yone.core.exception;

import org.kushinae.yone.core.enums.ErrorCode;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class TypeMappingException extends GlobalException {

    public TypeMappingException() {
        super(ErrorCode.DATA_TYPE_MAPPING_ERROR);
    }
}
