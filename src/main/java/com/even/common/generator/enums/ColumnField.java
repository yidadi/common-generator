package com.even.common.generator.enums;

/**
 * 表列字段枚举
 *
 * @author yidadi
 * @date 2016-09-02 15：43
 */
public enum ColumnField {

    /**
     * 表列名名称
     */
    COLUMN_NAME,

    /**
     * 是否允许为空
     */
    IS_NULLABLE,

    /**
     * 表列的类型
     */
    COlUMN_TYPE,


    /**
     * 表列的数据类型
     */
    DATA_TYPE,


    /**
     * 是否是主键
     */
    COLUMN_KEY,


    /**
     * 表列的注释
     */
    COLUMN_COMMENT,


    /**
     * 字符串最大长度
     */
    CHARACTER_MAXIMUM_LENGTH,


    /**
     * 表列默认值
     */
    COLUMN_DEFAULT;


    @Override
    public String toString() {
        return this.name();
    }
}
