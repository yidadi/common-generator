package com.even.common.generator.entity;

import com.even.common.generator.enums.SupportClassType;
import com.even.common.generator.util.TablePropertyUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 表属性解析器(整张表针对是否有某个属性特征的解析)
 *
 * @author yidadi
 * @date 2016-09-04 16：49
 */
public class TablePropertyParser implements Serializable {

    private static final long serialVersionUID = 1284773393591588467L;

    /**
     * 是否包含LocalDateTime
     */
    private Boolean includeLocalDateTime;

    /**
     * 是否包含LocalDate
     */
    private Boolean includeLocalDate;

    /**
     * 是否包含BigDecimal
     */
    private Boolean includeBigDecimal;

    private Boolean includeEnum;

    public TablePropertyParser() {
    }

    public TablePropertyParser(List<TableProperty> tableProperties) {
        Objects.requireNonNull(tableProperties, "Method argument tableProperties is null!");
        this.includeBigDecimal = this.parseSupportClassType(tableProperties, SupportClassType.BIG_DECIMAL);
        this.includeLocalDate = this.parseSupportClassType(tableProperties, SupportClassType.LOCAL_DATE);
        this.includeLocalDateTime = this.parseSupportClassType(tableProperties, SupportClassType.LOCAL_DATE_TIME);
    }


    public Boolean getIncludeLocalDateTime() {
        return includeLocalDateTime;
    }

    public void setIncludeLocalDateTime(Boolean includeLocalDateTime) {
        this.includeLocalDateTime = includeLocalDateTime;
    }

    public Boolean getIncludeLocalDate() {
        return includeLocalDate;
    }

    public void setIncludeLocalDate(Boolean includeLocalDate) {
        this.includeLocalDate = includeLocalDate;
    }

    public Boolean getIncludeBigDecimal() {
        return includeBigDecimal;
    }

    public void setIncludeBigDecimal(Boolean includeBigDecimal) {
        this.includeBigDecimal = includeBigDecimal;
    }

    public Boolean getIncludeEnum() {
        return includeEnum;
    }

    public void setIncludeEnum(Boolean includeEnum) {
        this.includeEnum = includeEnum;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 解析支持的类型
     *
     * @param tableProperties
     * @param type
     * @return
     */
    public boolean parseSupportClassType(List<TableProperty> tableProperties, SupportClassType type) {
        Objects.requireNonNull(tableProperties, "Method argument tableProperties is null!");
        Objects.requireNonNull(type, "Method argument type is null!");
        return tableProperties.stream().anyMatch(t -> type.getName().equals(t.getDataType()));
    }

    public static void main(String[] args) {
        List<TableProperty> propertyList = TablePropertyUtils.getPropertyList("vehicle", "vehicle");
        TablePropertyParser tablePropertyParser = new TablePropertyParser(propertyList);
        System.out.println(tablePropertyParser);
    }

}
