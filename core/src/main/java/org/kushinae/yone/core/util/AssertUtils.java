package org.kushinae.yone.core.util;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class AssertUtils {

    public static void hasText(String text, String message) {
        if (StringUtils.nonText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void nonText(String text, String message) {
        if (StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object obj, String message) {
        if (ObjectUtils.nonNull(obj)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object obj, String message) {
        if (ObjectUtils.isNull(obj)) {
            throw new IllegalArgumentException(message);
        }
    }


}
