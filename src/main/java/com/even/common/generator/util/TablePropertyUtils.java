package com.even.common.generator.util;


import com.even.common.generator.entity.TableProperty;
import com.even.common.generator.enums.ColumnDataType;
import com.even.common.generator.enums.ColumnField;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class TablePropertyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TablePropertyUtils.class);

    /**
     * 查询表具体属性的sql
     */
    private static final String TABLE_PROPERTY_SQL = "SELECT * FROM information_schema.`COLUMNS` t WHERE  t.table_schema=? AND t.table_name=?";

    /**
     * 查询表备注的sql
     */
    private static final String TABLE_COMMENT_SQL = "SELECT table_comment FROM information_schema.tables t WHERE t.table_schema =? AND  t.table_name=?";

    /**
     * 枚举数据类型
     */
    private static final String ENUM = "enum";

    public static List<TableProperty> getPropertyList(String dbName, String tableName) {
        Objects.requireNonNull(dbName, "Method argument dbName is null!");
        Objects.requireNonNull(tableName, "Method argument tableName is null!");
        List<TableProperty> tableProperties = Lists.newArrayList();
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(TABLE_PROPERTY_SQL);
            fillStatement(ps, dbName, tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                String columnName = rs.getString(ColumnField.COLUMN_NAME.name());
                Boolean isNull = rs.getBoolean(ColumnField.IS_NULLABLE.name());
                String columnType = rs.getString(ColumnField.COlUMN_TYPE.name());
                String dataType = rs.getString(ColumnField.DATA_TYPE.name());
                String columnKey = rs.getString(ColumnField.COLUMN_KEY.name());
                String columnComment = rs.getString(ColumnField.COLUMN_COMMENT.name());
                //判断是否大于了Integer的最大值
                Integer maxLength = 0;
                if (!dataType.equalsIgnoreCase("longtext")) {
                    maxLength = rs.getInt(ColumnField.CHARACTER_MAXIMUM_LENGTH.name());//最大长度
                }
                String columnDefault = rs.getString(ColumnField.COLUMN_DEFAULT.name());//,默认值
                TableProperty tableProperty = new TableProperty();
                tableProperty.setColumnName(columnName);
                tableProperty.setProperty(formatProperty(columnName));
                tableProperty.setColumnComment(columnComment);
                tableProperty.setNull(isNull);
                tableProperty.setColumnType(columnType);
                tableProperty.setMaxLength(maxLength);
                if (isEnum(dataType)) {
                    //TODO
                    tableProperty.setDataType(ColumnDataType.VARCHAR.getTypeName());
                } else {
                    tableProperty.setDataType(ColumnDataType.getDataType(dataType));
                }
                tableProperty.setEnum(isEnum(dataType));
                tableProperty.setPrimaryKey(isColumnKey(columnKey));
                tableProperty.setDefaultValue(columnDefault);
                tableProperty.setCapitalizeProperty(WordUtils.capitalize(formatProperty(columnName)));
                tableProperties.add(tableProperty);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            DBUtils.close(connection, ps, rs);
        }


        return tableProperties;
    }

    /**
     * 解析出枚举的类名
     *
     * @param columnComment
     * @return
     */
    private static String getEnumName(String columnComment) {
        //TODO
        return null;
    }

    /**
     * 是否是枚举类型
     *
     * @param dataType
     * @return
     */
    private static boolean isEnum(String dataType) {
        Objects.requireNonNull(dataType, "Method argument dataType is null!");
        return ENUM.equals(dataType);
    }

    private static void fillStatement(PreparedStatement stmt, Object... params)
            throws SQLException {
        boolean pmdKnownBroken = false;
        // check the parameter count, if we can
        ParameterMetaData pmd = null;
        if (!pmdKnownBroken) {
            pmd = stmt.getParameterMetaData();
            int stmtCount = pmd.getParameterCount();
            int paramsCount = params == null ? 0 : params.length;

            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }
        }

        // nothing to do here
        if (params == null) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                // VARCHAR works with many drivers regardless
                // of the actual column type. Oddly, NULL and
                // OTHER don't work with Oracle's drivers.
                int sqlType = Types.VARCHAR;
                if (!pmdKnownBroken) {
                    try {
                        /*
                         * It's not possible for pmdKnownBroken to change from
                         * true to false, (once true, always true) so pmd cannot
                         * be null here.
                         */
                        sqlType = pmd.getParameterType(i + 1);
                    } catch (SQLException e) {
                        pmdKnownBroken = true;
                    }
                }
                stmt.setNull(i + 1, sqlType);
            }
        }
    }

    /**
     * 获取一张表的备注
     *
     * @param dbName
     * @param tableName
     * @return
     */
    private static String getTableComment(Connection connection, String dbName, String tableName) {
        Objects.requireNonNull(connection, "Method argument connection is null!");
        Objects.requireNonNull(dbName, "Method argument dbName is null!");
        Objects.requireNonNull(tableName, "Method argument tableName is null!");
        PreparedStatement ps = null;
        ResultSet rs = null;
        String tableComment = null;
        try {
            ps = connection.prepareStatement(TABLE_COMMENT_SQL);
            fillStatement(ps, dbName, tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                tableComment = rs.getString("TABLE_COMMENT"); //表的注释
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return tableComment;
    }

    /**
     * 获取表的中文名字
     */
    public static List<String> getTableComments(List<String> entityObjects, String dbName) {
        Objects.requireNonNull(entityObjects, "Method argument entityObjects is null!");
        Objects.requireNonNull(dbName, "Method argument dbName is null!");
        Connection connection = DBUtils.getConnection();
        return entityObjects.stream().map(e -> getTableComment(connection, dbName, formatTableName(e))).collect(Collectors
                .toList());
    }

    /**
     * 如果字符串是包含了'_'则把字符串变成大写，例如par_value:parValue
     *
     * @param column
     * @return
     */
    public static String formatProperty(String column) {
        Objects.requireNonNull(column, "Method argument property is null!");
        List<String> strings = Splitter.on("_").omitEmptyStrings().trimResults().splitToList(column.toLowerCase());
        int size = strings.size();
        StringBuilder builder = new StringBuilder(strings.get(0));
        for (int i = 1; i < size; i++) {
            String temp = strings.get(i).substring(0, 1).toUpperCase() + strings.get(i).substring(1);
            builder.append(temp);
        }
        return builder.toString();
    }

    /**
     * 判断是否是主键
     */
    public static Boolean isColumnKey(String key) {
        Objects.requireNonNull(key, "Method argument key is null!");
        if ("PRI".equalsIgnoreCase(key)) {
            return true;
        }
        return false;
    }

    /**
     * 传入一个实体类名格式化成数据库的表名：例如一个实体名为OaAssetTypePar对应的表名为oa_asset_type_par
     *
     * @param entityName
     * @return
     */
    public static String formatTableName(String entityName) {
        Objects.requireNonNull(entityName, "Method argument entityName is null!");
        char[] chs = entityName.toCharArray();
        int len = chs.length;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < len - 1; i++) {
            if (Character.isUpperCase(chs[i]) && !Character.isUpperCase(chs[i + 1])) {
                String temp = entityName.substring(count, i).toLowerCase();
                count = i;
                builder.append(temp).append("_");
            }
        }
        String endWord = entityName.substring(count, len).toLowerCase();
        builder.append(endWord);
        return builder.toString();
    }

    private TablePropertyUtils() {

    }
}
