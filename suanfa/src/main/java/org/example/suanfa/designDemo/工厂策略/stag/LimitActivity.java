package org.example.suanfa.designDemo.工厂策略.stag;

import org.springframework.stereotype.Component;

@Component
public class LimitActivity implements ActivityType {


    @Override
    public ActicityEnum getType() {
        return ActicityEnum.LIMIT_ACTIVITY;
    }

    @Override
    public String getName() {
        return "限时活动";
    }

    @Override
    public String getDesc() {
        return "这是一个限时活动";
    }
}
