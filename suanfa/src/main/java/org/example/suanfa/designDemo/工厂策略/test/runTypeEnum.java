package org.example.suanfa.designDemo.工厂策略.test;

/**
 * @author heweitao538 2025/7/10
 */
public enum runTypeEnum {
    RUM_SINGLE_TYPE(1,"单机"), //单机
    RUN_DOUBLE_TYPE(2,"双机"), //双机
    RUN_THRID_TYPE(3,"三机");  //三机

    //枚举类型
    public int code;
    //运行名称
    public String name;
    runTypeEnum(int code, String name) {
        this.code=code;
        this.name=name;
    }

    public static runTypeEnum getByCode(int code) {
        runTypeEnum [] runTypeEnums=runTypeEnum.values();
        for(runTypeEnum runTypeEnum2:runTypeEnums){
            if(runTypeEnum2.code==code){
                return runTypeEnum2;
            }
        }
        return null;
    }
}
