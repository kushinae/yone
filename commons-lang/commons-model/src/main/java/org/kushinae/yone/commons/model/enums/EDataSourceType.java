package org.kushinae.yone.commons.model.enums;

/**
 * @author bnyte
 * @since 1.0.0
 */
public enum EDataSourceType {

    MY_SQL(0, "mysql", "any_version", "mysql"),
    ;

    EDataSourceType(Integer code, String name, String version, String pluginName) {
        this.code = code;
        this.name = name;
        this.version = version;
        this.pluginName = pluginName;
    }

    /**
     * data source type code
     */
    private Integer code;

    /**
     * data source type name.
     *  use underscore as delimiter and lowercase all characters
     */
    private String name;

    /**
     * data source version
     */
    private String version;

    /**
     * data source plugin name
     *  used when the value is not empty. used by default ${name}_${version} load plugin client
     */
    private String pluginName;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
}
