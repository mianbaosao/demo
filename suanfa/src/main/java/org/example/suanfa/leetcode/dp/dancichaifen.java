package org.example.suanfa.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 单词拆分
 * @Author:bread
 * @Date: 2025-01-19 14:39
 * dp[i] 表示字符串 s 的前 i 个字符（即 s.substring(0, i)）是否可以被字典中的单词拼接而成。
 */
public class dancichaifen {
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean result = wordBreak(s, wordDict);
        System.out.println(result);
    }
}
