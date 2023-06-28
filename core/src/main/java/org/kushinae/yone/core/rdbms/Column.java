package org.kushinae.yone.core.rdbms;

/**
 * @author kaisa.liu
 * @since 1.0.0
 */
public class Column {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段说明
     */
    private String comment;

    /**
     * 字段类型
     */
    private String datatype;

    /**
     * 类型名称
     */
    private String typename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", datatype='" + datatype + '\'' +
                ", typename='" + typename + '\'' +
                '}';
    }
}
