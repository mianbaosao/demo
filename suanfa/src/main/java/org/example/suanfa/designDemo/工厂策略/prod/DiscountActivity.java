package org.example.suanfa.designDemo.工厂策略.prod;

// 折扣活动策略
public class DiscountActivity implements ActivityStrategy {
    private double discountRate; // 折扣率

    public DiscountActivity(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public void applyActivity() {
        System.out.println("应用折扣活动，折扣率：" + discountRate);
    }
}