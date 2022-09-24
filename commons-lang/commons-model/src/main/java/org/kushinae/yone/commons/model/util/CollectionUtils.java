package org.kushinae.yone.commons.model.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class CollectionUtils {

    public static Boolean isEmpty(Collection<?> collection) {
        if (Objects.isNull(collection))
            return true;
        return collection.isEmpty();
    }

}
