package org.kushinae.yone.commons.model.properties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class RDBProperties extends Properties {

    private String database;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "RDBProperties{" +
                "database='" + database + '\'' +
                "} " + super.toString();
    }
}
