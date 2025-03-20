package leetcode.duoweidp;

/**
 * @Description: 最长公共子序列
 * @Author:bread
 * @Date: 2025-02-04 6:25
 * 其中 dp[i][j] 表示 text1 前 i 个字符 和 text2 前 j 个字符的最长公共子序列长度
 */
public class zixulie {
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 创建 DP 表，大小为 (m+1) x (n+1)，其中 dp[i][j] 表示 text1 前 i 个字符 和 text2 前 j 个字符的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {
                // 如果当前字符相同，则可以将其加入公共子序列
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 否则，取前一行或前一列的最大值（跳过一个字符的最大公共子序列）
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1="abcde",text2="ace";
        System.out.println(longestCommonSubsequence(text1,text2));
    }
}
