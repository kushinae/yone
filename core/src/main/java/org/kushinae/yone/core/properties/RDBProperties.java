package org.kushinae.yone.core.properties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class RDBProperties extends Properties {

    private String database;

    private String drive;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public RDBProperties database(String database) {
        setDatabase(database);
        return this;
    }

    public RDBProperties drive(String drive) {
        setDrive(drive);
        return this;
    }

    @Override
    public String toString() {
        return "RDBProperties{" +
                "database='" + database + '\'' +
                "} " + super.toString();
    }
}
