package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heweitao538 2025/7/10
 */
@Component
public class runTypeFactory implements InitializingBean {

    @Resource
    List<runType> runTypeList;

    Map<runTypeEnum,runType> enumrunTypeMap=new HashMap<>();

    public runType getType(int code){
        runTypeEnum runTypeEnum1 = runTypeEnum.getByCode(code);
        return enumrunTypeMap.get(runTypeEnum1);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for(runType runType : runTypeList){
            enumrunTypeMap.put(runType.getType(),runType);
        }
    }
}
