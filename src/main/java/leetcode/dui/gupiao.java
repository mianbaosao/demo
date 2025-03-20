package leetcode.dui;

/**
 * @Description: 买卖股票的最佳时机
 * @Author:bread
 * @Date: 2025-01-17 11:16
 * 这里就是知道的就是一开始默认第一个为最小值   然后来利润就行
 */
public class gupiao {
    public static int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
