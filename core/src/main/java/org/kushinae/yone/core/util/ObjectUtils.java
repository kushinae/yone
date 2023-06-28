package org.kushinae.yone.core.util;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class ObjectUtils {

    public static boolean isNull (Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean nonNull (Object obj) {
        return Objects.nonNull(obj);
    }

}
