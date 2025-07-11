package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heweitao538 2025/7/11
 */
@Component
public class StudyTypeFactory implements InitializingBean {
    @Resource
    List<StudyType> studyTypeList;

    Map<StudyTypeEnum,StudyType> studyTypeMap = new HashMap<>();

    public StudyType getByCode(int val){
        StudyTypeEnum studyTypeEnum= StudyTypeEnum.getByCode(val);
        return studyTypeMap.get(studyTypeEnum);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        for(StudyType studyType:studyTypeList){
            studyTypeMap.put(studyType.getEnum(),studyType);
        }
    }
}
