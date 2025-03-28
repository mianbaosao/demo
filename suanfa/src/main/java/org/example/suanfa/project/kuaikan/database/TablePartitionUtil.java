package org.example.suanfa.project.kuaikan.database;

import lombok.extern.slf4j.Slf4j;


/** 分表工具类. */
@Slf4j
public class TablePartitionUtil {

    private static final String MID_PARTITION_NAME = "partition_count_middle";
    private static final String PARTITION_COUNT_SMALL = "2";
    private static final String PARTITION_COUNT_MIDDLE = "128";

    public static String getProperty(String key) {
        if (MID_PARTITION_NAME.equals(key)) {
            return Settings.getEnvironment().le(Environment.PREVIEW) ? PARTITION_COUNT_SMALL : PARTITION_COUNT_MIDDLE;
        }
        return PARTITION_COUNT_SMALL;
    }

    public static String getTableName(TablePartition tablePartitionEnum, long userId) {
        return String.format(tablePartitionEnum.getTableFormat(), tablePartitionEnum.getTableNamePrefix(), getPartitionNum(tablePartitionEnum, userId));
    }

    // 得到分表对应的表名称.
    public static String getTableName(TablePartition tablePartitionEnum, int currentTable) {
        return String.format(tablePartitionEnum.getTableFormat(), tablePartitionEnum.getTableNamePrefix(), currentTable);
    }

    public static long getPartitionNum(TablePartition tablePartitionEnum, long userId) {
        return Math.abs(userId % tablePartitionEnum.getTableCount());
    }

    public static void main(String[] args) {
        System.out.println(Math.abs(1751035 % 128));
    }

    public static String getTableNameByDate(TablePartition tablePartitionEnum, int year, int week) {
        return String.format(tablePartitionEnum.getTableFormat(), tablePartitionEnum.getTableNamePrefix(), year, week);
    }

    public static String adaptTableNameByDate(String tableName) {
        return tableName + "_" + DateUtil.getCurrentMonth();
    }

    public static String appendSuffix(String tableName, String suffix) {
        return tableName + "_" + suffix;
    }
}
