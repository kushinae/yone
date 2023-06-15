package org.kushinae.yone.commons.model.properties;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class Properties {

    private String host;

    private Integer port;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties host(String host) {
        this.setHost(host);
        return this;
    }

    public Properties port(Integer port) {
        this.setPort(port);
        return this;
    }

    public Properties username(String username) {
        this.setUsername(username);
        return this;
    }

    public Properties password(String password) {
        this.setUsername(password);
        return this;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
