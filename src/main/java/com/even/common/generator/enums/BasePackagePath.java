package com.even.common.generator.enums;

import java.io.File;

/**
 * 基础包路径
 *
 * @author yidadi
 * @date 2016-09-04 23：47
 */
public enum BasePackagePath {

    /**
     * 基础域名路径
     */
    BASE("com/haixue"),

    /**
     * Main包路径
     */
    MAIN("src/main/java"),

    /**
     * Test包路径
     */
    TEST("src/test/java");

    private String path;

    BasePackagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path + File.separator;
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}
