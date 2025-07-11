package org.example.suanfa.designDemo.工厂策略.test;

/**
 * @author heweitao538 2025/7/11
 */
public enum StudyTypeEnum {
    STUDY_TYPE_ONLINR(1,"线上"),
    STUDY_TYPE_OFFLINE(2,"线下");

    //code
    private int code;
    //名称
    private String name;

    StudyTypeEnum(int i, String name) {
        this.code=code;
        this.name=name;
    }

    public static StudyTypeEnum getByCode(int val){
        StudyTypeEnum [] studyTypeEnums=StudyTypeEnum.values();
        for (StudyTypeEnum studyTypeEnum : studyTypeEnums) {
            if(studyTypeEnum.code==val){
                return studyTypeEnum;
            }
        }
        return null;
    }
}
