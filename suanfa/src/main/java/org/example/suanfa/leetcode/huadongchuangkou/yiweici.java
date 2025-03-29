package org.example.suanfa.leetcode.huadongchuangkou;

import java.util.*;

/**
 * @Description: 找到字符串中所有的字母异位词
 * @Author:bread
 * @Date: 2024-12-02 22:06
 * 重点就是根据滑动窗口来解决，滑动窗口的方式也很多，我还是觉得第一钟方法好用
 *
 */
public class yiweici {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = s.length();
        int n = p.length();
        if (n == 0 || m < n) return res;
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        // 初始化p的字符计数和s的初始窗口计数
        for (int i = 0; i < n; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount)) {
            res.add(0);
        }
        // 滑动窗口，每次移动一位
        for (int i = n; i < m; i++) {
            // 移除窗口左端的字符
            char leftChar = s.charAt(i - n);
            sCount[leftChar - 'a']--;
            // 添加窗口右端的新字符
            char rightChar = s.charAt(i);
            sCount[rightChar - 'a']++;
            // 检查字符计数是否匹配
            if (Arrays.equals(pCount, sCount)) {
                res.add(i - n + 1);
            }
        }
        return res;
    }
    /**
     * 另一种滑动窗口的方法
     */
   /* public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if (n < m) return res;
        // 统计p中字符的频率
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        // 队列记录当前窗口的字符
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(s.charAt(i));
            // 当队列达到大小m时，检查异位词
            if (queue.size() == m) {
                // 统计当前窗口字符的频率
                int[] sCount = new int[26];
                for (char c : queue) {
                    sCount[c - 'a']++;
                }
                // 比较两个频率数组
                if (Arrays.equals(pCount, sCount)) {
                    res.add(i - m + 1); // 添加起始索引
                }
                // 移除队列最左侧的元素以滑动窗口
                queue.poll();
            }
        }
        return res;
    }*/
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
}
