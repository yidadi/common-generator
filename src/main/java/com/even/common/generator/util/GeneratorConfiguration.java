/*
 * @(#)Codematic.java
 *
 * Copyright 2012 Xinhua Online, Inc. All rights reserved.
 */
package com.even.common.generator.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static java.io.File.separator;


/**
 * 获取自动生成代码的配置信息
 *
 * @author yidadi
 * @version 1.0
 * @date 2016-09-02 14:00:02
 */
public class GeneratorConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorConfiguration.class);

    /**
     * 数据库ip地址或者网址
     */
    public static String ip;

    /**
     * 数据库的端口
     */
    public static String port;

    /**
     * 数据库用户名
     */
    public static String user;

    /**
     * 数据库密码
     */
    public static String password;

    /**
     * 具体数据库名称
     */
    public static String dbName;

    /**
     * 项目的根路径
     */
    public static String projectPath;

    /**
     * 项目名称
     */
    public static String projectName;

    /**
     * entity生成的路径
     */
    public static String entityPath;
    public static String companyName;

    /**
     * dao生成的路径
     */
    public static String daoPath;

    /**
     * service生成的路径
     */
    public static String servicePath;

    /**
     * 需要生成代码的实体类
     */
    public static List<String> entityObjects = Lists.newArrayList();

    // 获取url、user、password等配置信息
    static {
        InputStream input = null;
        Properties properties = new Properties();
        input = GeneratorConfiguration.class.getClassLoader().getResourceAsStream(
                "generator.properties");
        try {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.error("未找到生成代码的配置文件-generator.properties " + e.getMessage());
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭失败  " + e.getMessage());
                }
            }
        }
        //初始化配置属性
        init(properties);

    }

    /**
     * 初始化配置属性
     *
     * @param properties
     */
    private static void init(Properties properties) {
        Field[] fields = GeneratorConfiguration.class.getFields();
        for (Field field : fields) {
            try {
                Object o = field.get(GeneratorConfiguration.class);
                if (o instanceof List) {
                    field.set(GeneratorConfiguration.class, getListProperty(properties.getProperty(field.getName())));
                } else {
                    field.set(GeneratorConfiguration.class, getValidProperty(properties.getProperty(field.getName())));
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Reflection  GeneratorConfiguration Properties is error" + e.getMessage());
            }

        }
    }

    private static List<String> getListProperty(String entityObjects) {
        return Splitter.on(",").omitEmptyStrings().trimResults().splitToList(getValidProperty(entityObjects));
    }

    private static String getValidPath(String path) {
        path = getValidProperty(path);
        if (!path.startsWith(separator)) {
            path = separator + path;
        }
        if (!path.endsWith(separator)) {
            path = path + separator;
        }
        return path;
    }

    private static String getValidProperty(String property) {
        Objects.requireNonNull(property, "Method argument property is null!");
        return property;
    }

}