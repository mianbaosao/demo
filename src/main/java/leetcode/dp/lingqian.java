package leetcode.dp;

import java.util.Arrays;

/**
 * @Description: 零钱兑换
 * @Author:bread
 * @Date: 2025-01-18 13:38
 * dp[i] 表示凑成金额 i 所需的最少硬币数量
 */
public class lingqian {
    public static int coinChange(int[] coins, int amount) {
        int []dp=new int[amount+1];
        Arrays.fill(dp,999999);
        dp[0]=0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if(i>=coins[j]) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }
        }
        if(dp[amount]==999999){
            return -1;
        }else{
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        int []coins={1,2,5};
        int amount=11;
        System.out.println(coinChange(coins,amount));
    }
}
