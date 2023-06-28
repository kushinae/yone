package org.kushinae.yone.mysql.model;

import jakarta.persistence.AttributeConverter;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public interface AttributeConverterSupport<T extends AttributeConverter> {

    T getConvert();

}
