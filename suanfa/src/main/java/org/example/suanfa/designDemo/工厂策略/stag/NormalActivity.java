package org.example.suanfa.designDemo.工厂策略.stag;

import org.springframework.stereotype.Component;

@Component
public class NormalActivity implements ActivityType {


    @Override
    public ActicityEnum getType() {
        return ActicityEnum.NORMAL_ACTIVITY;
    }

    @Override
    public String getName() {
        return "3";
    }

    @Override
    public String getDesc() {
        return "333333";
    }
}
