package org.example.suanfa.designDemo.工厂策略.test;

import org.springframework.stereotype.Component;

/**
 * @author heweitao538 2025/7/10
 */
@Component
public class singleRun implements runType{
    @Override
    public String getName() {
        return "我是单机";
    }

    @Override
    public void test() {
        System.out.println("这是双击right here");
    }

    @Override
    public runTypeEnum getType() {
        return runTypeEnum.RUM_SINGLE_TYPE;
    }
}
