package org.kushinae.yone.client.test;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public interface IdGenerator<T> {

    T generate();

    int getIdType();

}
