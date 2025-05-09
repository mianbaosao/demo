package org.example.suanfa.designDemo.工厂策略.stag;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivityFactory implements InitializingBean {

    @Resource
    List<ActivityType> activityTypes;

    Map<ActicityEnum,ActivityType> activityMap = new HashMap<>();

    public ActivityType getActivityType(int code) {
        ActicityEnum acticityEnum= ActicityEnum.getByCode(code);
        return activityMap.get(acticityEnum);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (ActivityType activityType : activityTypes) {
            activityMap.put(activityType.getType(),activityType);
        }
    }
}
