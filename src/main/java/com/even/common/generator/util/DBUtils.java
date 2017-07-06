package com.even.common.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Objects;

public class DBUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);

    private static String url = "jdbc:mysql://" + GeneratorConfiguration.ip + ":"
            + GeneratorConfiguration.port + "/information_schema?useunicode=true&characterEncoding=utf8";

    private static String user = GeneratorConfiguration.user;

    private static String password = GeneratorConfiguration.password;

    // 通过静态代码块加载Driver
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("MySQL数据库驱动文件没有找到  " + e.getMessage());
        }
    }

    /**
     * 提供获取Connection的getConnection()
     */
    public static Connection getConnection() {
        try {
            LOGGER.info("mysql url="+ url);
            Connection connection = DriverManager.getConnection(url, user, password);
            Objects.requireNonNull(connection, "Method argument connection is null!");
            return connection;
        } catch (SQLException e) {
            LOGGER.error("MySQL数据库连接没有获取到  " + e.getMessage());
        }
        return null;
    }

    private DBUtils() {

    }


    /**
     * 定义一个关闭连接资源的方法
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement,
                             ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.error("关闭连接等资源失败  " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(url);
        System.out.println(password);
        System.out.println(user);
        System.out.println(connection);
    }

}
