package org.kushinae.yone.mysql.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public enum EJobType implements CodeSupport<String>, AttributeConverterSupport<EJobType.Convert> {
    GENERATE("generate"),
    ;

    @Override
    public Convert getConvert() {
        return new Convert();
    }

    @Converter(autoApply = true)
    public static class Convert implements AttributeConverter<EJobType, String> {
        @Override
        public String convertToDatabaseColumn(EJobType attribute) {
            return attribute.code;
        }

        @Override
        public EJobType convertToEntityAttribute(String dbData) {
            return matchByCode(dbData);
        }

    }

    public static EJobType matchByCode(String code) {
        for (EJobType value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported job type of the " + code);
    }

    private final String code;

    EJobType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
