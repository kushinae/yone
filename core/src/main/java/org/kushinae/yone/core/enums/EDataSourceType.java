package org.kushinae.yone.core.enums;

import org.kushinae.yone.core.exception.UnsupportedDataSourceException;

/**
 * @author bnyte
 * @since 1.0.0
 */
public enum EDataSourceType {

    MY_SQL(0, "mysql", null, "mysql", "SELECT VERSION()", "com.mysql.cj.jdbc.Driver"),
    SQL_SERVER(1, "sql_server", null, "sql_server", "SELECT VERSION()", "com.mysql.cj.jdbc.Driver"),
    ;

    public static EDataSourceType code(Integer typeCode) {
        EDataSourceType[] values = values();
        for (EDataSourceType value : values) {
            if (value.getCode().equals(typeCode)) {
                return value;
            }
        }
        throw new UnsupportedDataSourceException();
    }

    EDataSourceType(Integer code, String name, String version, String pluginName, String testConnectionScript, String drive) {
        this.code = code;
        this.name = name;
        this.version = version;
        this.pluginName = pluginName;
        this.testConnectionScript = testConnectionScript;
        this.drive = drive;
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

    /**
     * 测试连通性脚本
     */
    private String testConnectionScript;

    /**
     * 驱动类
     */
    private String drive;

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

    public String getTestConnectionScript() {
        return testConnectionScript;
    }

    public void setTestConnectionScript(String testConnectionScript) {
        this.testConnectionScript = testConnectionScript;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
}
