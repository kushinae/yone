package org.kushinae.yone.core.client;

import org.kushinae.yone.core.enums.EDataSourceType;
import org.kushinae.yone.core.exception.ClientNotFoundException;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class ClientFactory {

    // added java unchecked code style warnings
    @SuppressWarnings("unchecked")
    protected static IClient createClient(EDataSourceType dataSourceType) {
        ServiceLoader<?> clients = ServiceLoader.load(IClient.class);
        Iterator<IClient> clientIterator = (Iterator<IClient>) clients.iterator();
        while (clientIterator.hasNext()) {
            IClient IClient = clientIterator.next();
            if (IClient.getDataSourceTypeCode().equals(dataSourceType.getCode())) {
                return IClient;
            }
        }

        throw new ClientNotFoundException();
    }

}
