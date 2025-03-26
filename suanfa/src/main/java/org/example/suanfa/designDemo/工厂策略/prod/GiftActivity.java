package org.example.suanfa.designDemo.工厂策略.prod;

// 赠品活动策略
public class GiftActivity implements ActivityStrategy {
    private String giftName; // 赠品名称

    public GiftActivity(String giftName) {
        this.giftName = giftName;
    }

    @Override
    public void applyActivity() {
        System.out.println("应用赠品活动，赠送：" + giftName);
    }
}