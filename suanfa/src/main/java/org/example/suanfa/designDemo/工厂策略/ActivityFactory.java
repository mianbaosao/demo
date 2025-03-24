package org.example.suanfa.designDemo.工厂策略;

// 活动工厂类
public class ActivityFactory {
    public static ActivityStrategy createActivity(ActivityType type, Object... params) {
        switch (type) {
            case DISCOUNT:
                return new DiscountActivity((Double) params[0]); // 折扣率
            case FULL_REDUCTION:
                return new FullReductionActivity((Double) params[0], (Double) params[1]); // 满减条件和减额
            case GIFT:
                return new GiftActivity((String) params[0]); // 赠品名称
            default:
                throw new IllegalArgumentException("未知的活动类型");
        }
    }
}