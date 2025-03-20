package org.example.suanfa.leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分割回文串
 * @Author: bread
 * @Date: 2025-01-13 11:29
 * 这个题目的话还是得注意合理运用好start这个参数，然后搞清楚回溯的条件
 */
public class fengehuiwenchuan {
    public static List<List<String>> res = null; // 结果列表
    public static List<String> c = null; // 当前路径

    public static List<List<String>> partition(String s) {
        res = new ArrayList<>();
        c = new ArrayList<>();
        backtracking(s, 0, new StringBuilder());
        return res;
    }
    private static void backtracking(String s, int start, StringBuilder sb) {
        // 如果已到达字符串末尾，将当前路径加入结果集
        if (start == s.length()) {
            res.add(new ArrayList<>(c));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (check(sb)) {
                c.add(sb.toString());
                backtracking(s, i + 1, new StringBuilder());
                c.remove(c.size() - 1);
            }
//            sb.setLength(0);
        }
    }
    public static boolean check(StringBuilder sb) {
        int left = 0, right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = partition(s);
        System.out.println(result);
    }
}
