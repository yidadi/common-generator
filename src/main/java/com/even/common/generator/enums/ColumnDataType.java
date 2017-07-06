package com.even.common.generator.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 表列数据类型
 *
 * @author yidadi
 * @date 2016-09-02 16：10
 */
public enum ColumnDataType {

    INT(SupportClassType.INTEGER),

    TINYINT(SupportClassType.INTEGER),

    BIGINT(SupportClassType.LONG),

    BIT(SupportClassType.BOOLEAN),

    VARCHAR(SupportClassType.STRING),

    CHAR(SupportClassType.STRING),

    TIMESTAMP(SupportClassType.LOCAL_DATE_TIME),

    DATETIME(SupportClassType.LOCAL_DATE_TIME),

    DATE(SupportClassType.LOCAL_DATE),

    DECIMAL(SupportClassType.BIG_DECIMAL);

    /**
     * 获取具体的列返回类型,没有定义的类型,默认返回的是String类型的
     *
     * @param type
     * @return
     */
    public static String getDataType(String type) {
        Objects.requireNonNull(type, "Method argument type is null!");
        Optional<ColumnDataType> first = Arrays.stream(ColumnDataType.values()).filter(v -> v.name().equals(type.toUpperCase())).findFirst();
        return first.isPresent() ? first.get().getTypeName() : VARCHAR.getTypeName();
    }

    private SupportClassType type;

    ColumnDataType(SupportClassType type) {
        this.type = type;
    }

    public SupportClassType getType() {
        return this.type;
    }

    /**
     * 获取类型的Name
     *
     * @return
     */
    public String getTypeName() {
        return this.type.getName();
    }

}
