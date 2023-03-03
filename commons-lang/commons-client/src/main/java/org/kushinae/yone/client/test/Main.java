package org.kushinae.yone.client.test;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class Main {

    public static void main(String[] args) {
        IdGenerator<?> instance = IdGeneratorFactory.getInstance(0);
        System.out.println(instance.generate().getClass().getName());

        IdGenerator<String> instance1 = IdGeneratorFactory.getInstance(0, String.class);
        System.out.println(instance1.generate());
    }

}
