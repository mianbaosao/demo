package org.example.suanfa.leetcode.dp;

/**
 * 分割等和子集
 */
public class denghe {

    public static void main(String[] args) {
        int[] nums = { 1, 5, 11, 5 };
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }        int target = sum / 2;
        // dp[i] 表示能否用数组中的数字凑出和 i
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
