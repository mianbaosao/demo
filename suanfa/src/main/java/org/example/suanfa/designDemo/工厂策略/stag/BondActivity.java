package org.example.suanfa.designDemo.工厂策略.stag;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BondActivity implements ActivityType {


    @Override
    public ActicityEnum getType() {
        return ActicityEnum.BOND_ACTIVITY;
    }

    @Override
    public String getName() {
        return "2";
    }

    @Override
    public String getDesc() {
        return "22222";
    }
}
