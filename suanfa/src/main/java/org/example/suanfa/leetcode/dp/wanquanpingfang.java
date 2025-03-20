package org.example.suanfa.leetcode.dp;

import java.util.Arrays;

/**
 * @Description: 完全平方数
 * @Author:bread
 * @Date: 2025-01-18 13:21
 * dp 是一个长度为 n + 1 的数组。
 */
public class wanquanpingfang {
    public static int numSquares(int n) {
        int []dp=new int[n+1];
        Arrays.fill(dp,9999);
        dp[0]=0;
        for (int i =1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=13;
        System.out.println(numSquares(n));
    }
}
