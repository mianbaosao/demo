package org.example.suanfa.leetcode.dp;

/**
 * 编辑距离
 */
public class bianji {
    public static int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        // 初始化第一列：将 word1 变为空字符串，需要删除所有字符
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        // 初始化第一行：将空字符串变为 word2，需要插入所有字符
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        // 动态规划填表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 如果字符相同，不需要额外操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果字符不同，取删除、插入、替换中的最小值加一
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1,    // 删除
                                    dp[i][j - 1] + 1),   // 插入
                            dp[i - 1][j - 1] + 1);         // 替换
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("hello", "world"));
    }
}
