package com.even.common.generator.enums;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 支持的Java类型
 *
 * @author yidadi
 * @date 2016-09-04 17：04
 */
public enum SupportClassType {

    INTEGER(Integer.class),

    LONG(Long.class),

    STRING(String.class),

    BOOLEAN(Boolean.class),

    BIG_DECIMAL(BigDecimal.class),

    LOCAL_DATE(LocalDate.class),

    LOCAL_DATE_TIME(LocalDateTime.class);

    private static String getSimpleName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    private Class<?> value;

    SupportClassType(Class<?> value) {
        this.value = value;
    }

    public String getName() {
        return this.value.getSimpleName();
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
