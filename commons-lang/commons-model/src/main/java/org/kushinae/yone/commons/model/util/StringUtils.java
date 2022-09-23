package org.kushinae.yone.commons.model.util;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class StringUtils {

    public static Boolean hasText(String text) {
        if (Objects.isNull(text)) {
            return false;
        }
        return text.length() != 0;
    }

}
