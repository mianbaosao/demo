package org.example.suanfa.project.kuaikan.database;

import org.apache.commons.lang3.math.NumberUtils;



public enum TablePartition {
    CARD_BATTLE_USER_CARD(1, "card_battle_user_card_", NumberUtils.toInt(TablePartitionUtil.getProperty("partition_count_middle"), 128), "%s%s"),
    CARD_BATTLE_USER_PROP(2, "card_battle_user_prop_", NumberUtils.toInt(TablePartitionUtil.getProperty("partition_count_middle"), 128), "%s%s"),
    ;

    private int code;
    private String tableNamePrefix;
    private int tableCount;
    private String tableFormat;

    TablePartition(int code, String tableNamePrefix, int tableCount, String tableFormat) {
        this.code = code;
        this.tableNamePrefix = tableNamePrefix;
        this.tableCount = tableCount;
        this.tableFormat = tableFormat;
    }

    public int getCode() {
        return code;
    }

    public String getTableNamePrefix() {
        return tableNamePrefix;
    }

    public int getTableCount() {
        return tableCount;
    }

    public String getTableFormat() {
        return tableFormat;
    }

    public static TablePartition findByCode(int code) {
        for (TablePartition tablePartitionEnum : TablePartition.values()) {
            if (tablePartitionEnum.code == code) {
                return tablePartitionEnum;
            }
        }
        return null;
    }
}
