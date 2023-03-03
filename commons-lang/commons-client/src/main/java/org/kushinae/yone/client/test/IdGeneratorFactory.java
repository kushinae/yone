package org.kushinae.yone.client.test;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class IdGeneratorFactory {
    public static IdGenerator<?> getInstance(int code) {
        IdGenerator stringIdGenerator = new StringIdGenerator();
        return stringIdGenerator.getIdType() == code ? stringIdGenerator : null;
    }

    public static <T> IdGenerator<T> getInstance(int code, Class<T> target) {
        IdGenerator<T> stringIdGenerator = (IdGenerator<T>) new StringIdGenerator();
        return stringIdGenerator.getIdType() == code ? stringIdGenerator : null;
    }
}
