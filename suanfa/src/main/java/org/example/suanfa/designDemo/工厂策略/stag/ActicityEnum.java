package org.example.suanfa.designDemo.工厂策略.stag;

public enum ActicityEnum {
    LIMIT_ACTIVITY(1,"限时活动"),
    BOND_ACTIVITY(2,"羁绊活动"),
    NORMAL_ACTIVITY(3,"基础活动");

    public int code;
    public String decr;
    ActicityEnum(int code, String decr) {
        this.code = code;
        this.decr = decr;
    }
    public static ActicityEnum getByCode(int code) {
        ActicityEnum[] values = ActicityEnum.values();
        for (ActicityEnum value : values) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
