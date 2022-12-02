package org.kushinae.yone.client;

import org.kushinae.yone.commons.model.enums.EDataSourceType;
import org.kushinae.yone.commons.model.exception.ClientNotFoundException;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientFactory {

    // added java unchecked code style warnings
    @SuppressWarnings("unchecked")
    protected static <T> IClient<T> createClient(EDataSourceType dataSourceType, Class<T> resultType) {
        //            Constructor<T> constructor = resultType.getConstructor();
//            T instance = constructor.newInstance();
        ServiceLoader<T> clients = (ServiceLoader<T>) ServiceLoader.load(IClient.class);
        Iterator<IClient<T>> clientIterator = (Iterator<IClient<T>>) clients.iterator();
        while (clientIterator.hasNext()) {
            IClient<T> IClient = clientIterator.next();
            if (IClient.getDataSourceTypeCode().equals(dataSourceType.getCode())) {
                return IClient;
            }
        }

        throw new ClientNotFoundException();
    }

    // added java unchecked code style warnings
    @SuppressWarnings("unchecked")
    protected static IClient<?> createClient(EDataSourceType dataSourceType) {
        ServiceLoader<?> clients = ServiceLoader.load(IClient.class);
        Iterator<IClient<?>> clientIterator = (Iterator<IClient<?>>) clients.iterator();
        while (clientIterator.hasNext()) {
            IClient<?> IClient = clientIterator.next();
            if (IClient.getDataSourceTypeCode().equals(dataSourceType.getCode())) {
                return IClient;
            }
        }

        throw new ClientNotFoundException();
    }

}
