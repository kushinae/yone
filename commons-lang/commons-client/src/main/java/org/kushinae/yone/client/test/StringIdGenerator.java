package org.kushinae.yone.client.test;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class StringIdGenerator implements IdGenerator<String> {

    @Override
    public String generate() {
        return "q";
    }

    @Override
    public int getIdType() {
        // string 0, int or integer 1
        return 0;
    }


}
