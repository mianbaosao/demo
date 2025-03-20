package org.example.suanfa.leetcode.dp;

/**
 * @Description: 爬楼梯
 * @Author:bread
 * @Date: 2025-01-18 11:43
 */
public class palouti {
    public static int climbStairs(int n) {
        if(n==1){
            return 1;
        }
        int []dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for (int i = 3; i <= n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=2;
        System.out.println(climbStairs(2));
    }
}
