package org.example.suanfa.designDemo.工厂策略;

// 满减活动策略
public class FullReductionActivity implements ActivityStrategy {
    private double fullAmount; // 满减条件
    private double reductionAmount; // 减额

    public FullReductionActivity(double fullAmount, double reductionAmount) {
        this.fullAmount = fullAmount;
        this.reductionAmount = reductionAmount;
    }

    @Override
    public void applyActivity() {
        System.out.println("应用满减活动，满 " + fullAmount + " 减 " + reductionAmount);
    }
}