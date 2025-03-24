package org.example.suanfa.designDemo.工厂策略;

public class Client {
    public static void main(String[] args) {
        // 创建折扣活动
        ActivityStrategy discountActivity = ActivityFactory.createActivity(ActivityType.DISCOUNT, 0.8);
        discountActivity.applyActivity();

        // 创建满减活动
        ActivityStrategy fullReductionActivity = ActivityFactory.createActivity(ActivityType.FULL_REDUCTION, 100.0, 20.0);
        fullReductionActivity.applyActivity();

        // 创建赠品活动
        ActivityStrategy giftActivity = ActivityFactory.createActivity(ActivityType.GIFT, "精美礼品");
        giftActivity.applyActivity();
    }
}