package org.kushinae.yone.client.actuator;

import java.sql.SQLException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public interface Actuator<T> {

    Boolean execute(String script) throws SQLException;

}
