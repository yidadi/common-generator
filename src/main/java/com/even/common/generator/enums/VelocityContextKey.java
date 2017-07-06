package com.even.common.generator.enums;

/**
 * 模板的键值
 *
 * @author yidadi
 * @date 2016-09-05 10：26
 */
public enum VelocityContextKey {

    /**
     * 实体类
     */
    UPPER_ENTITY("Entity"),

    /**
     * 实体类首字母小写
     */
    LOWER_ENTITY("entity"),

    /**
     * 表名
     */
    TABLE_NAME("TableName"),

    /**
     * 表属性列表
     */
    PROPERTY_LIST("propertyList"),

    /**
     * 实体注释(备注)
     */
    ENTITY_COMMENT("entityComment"),

    /**
     * 表属性解析器
     */
    PARSER("parser"),

    /**
     * 项目名称
     */
    PROJECT_NAME("projectName"),
    COMPANY_NAME("companyName"),
    /**
     * 项目名称首字母大写
     */
    UPPER_PROJECT_NAME("ProjectName");

    private String key;

    VelocityContextKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
