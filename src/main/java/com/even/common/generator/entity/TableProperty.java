package com.even.common.generator.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class TableProperty implements Serializable {

    private static final long serialVersionUID = 1743846400269905474L;

    /**
     * java实体类属性名
     */
    private String property;

    /**
     * 是否允许为空
     */
    private Boolean isNull;

    /**
     * 是否是主键
     */
    private Boolean isPrimaryKey;

    /**
     * 表字段注释
     */
    private String columnComment;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 如果是字符串类型的，其最大长度是多少
     */
    private Integer maxLength;
    /**
     * 默认值
     */
    private Serializable defaultValue;

    /**
     * 表字段名
     */
    private String columnName;

    /**
     * 表字段类型
     */
    private String columnType;

    /**
     * 大写属性名字的首字母,用于生成实体类
     */
    private String capitalizeProperty;

    /**
     * 是否是枚举类型
     */
    private Boolean isEnum;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Boolean getNull() {
        return isNull;
    }

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Serializable getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Serializable defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getCapitalizeProperty() {
        return capitalizeProperty;
    }

    public void setCapitalizeProperty(String capitalizeProperty) {
        this.capitalizeProperty = capitalizeProperty;
    }

    public Boolean getEnum() {
        return isEnum;
    }

    public void setEnum(Boolean anEnum) {
        isEnum = anEnum;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
