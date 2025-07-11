package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.stereotype.Component;

/**
 * @author heweitao538 2025/7/11
 */
@Component
public class StudyOffLine implements StudyType{
    @Override
    public StudyTypeEnum getEnum() {
        return StudyTypeEnum.STUDY_TYPE_OFFLINE;
    }

    @Override
    public void getName() {
        System.out.println("bread线下");
    }

    @Override
    public void test() {
        System.out.println("here is aaaaaaaaa test");
    }
}
