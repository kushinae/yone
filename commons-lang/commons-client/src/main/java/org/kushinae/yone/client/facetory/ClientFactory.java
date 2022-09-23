package org.kushinae.yone.client.facetory;

import org.kushinae.yone.client.Client;
import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.exception.ClientNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientFactory {

    public static <T> Client<T> createClient(EDataSourceType dataSourceType, Class<T> resultType) {
        try {
            Constructor<T> constructor = resultType.getConstructor();
            T instance = constructor.newInstance();
            ServiceLoader<T> clients = (ServiceLoader<T>) ServiceLoader.load(Client.class);
            Iterator<Client<T>> clientIterator = (Iterator<Client<T>>) clients.iterator();
            while (clientIterator.hasNext()) {
                Client<T> client = clientIterator.next();
                if (client.getDataSourceTypeCode().equals(dataSourceType.getCode())) {
                    return client;
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        throw new ClientNotFoundException();
    }

    public static Client<?> createClient(EDataSourceType dataSourceType) {
        ServiceLoader<?> clients = ServiceLoader.load(Client.class);
        Iterator<Client<?>> clientIterator = (Iterator<Client<?>>) clients.iterator();
        while (clientIterator.hasNext()) {
            Client<?> client = clientIterator.next();
            if (client.getDataSourceTypeCode().equals(dataSourceType.getCode())) {
                return client;
            }
        }

        return null;
    }

}
