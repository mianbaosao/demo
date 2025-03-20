package leetcode.zichuan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Description: 最小覆盖子串
 * @Author:bread
 * @Date: 2024-12-03 19:53
 */
public class fugai {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        // 创建一个哈希表来存储t中每个字符的频率
        Map<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }
        // 滑动窗口的左右指针
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;  // 记录最小子串的起始位置
        // 记录窗口中每个字符的频率
        Map<Character, Integer> windowFreq = new HashMap<>();
        // 记录窗口中已满足的t字符数量
        int matched = 0;
        // 开始滑动窗口
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowFreq.put(rightChar, windowFreq.getOrDefault(rightChar, 0) + 1);
            // 如果当前字符的数量在窗口中满足t中该字符的数量，更新matched
            if (tFreq.containsKey(rightChar) && windowFreq.get(rightChar) <= tFreq.get(rightChar)) {
                matched++;
            }
            // 当窗口包含t中所有字符时，尝试收缩窗口
            while (matched == t.length()) {
                int windowLength = right - left + 1;
                if (windowLength < minLength) {
                    minLength = windowLength;
                    minLeft = left;
                }
                // 移动左指针，缩小窗口
                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                // 如果当前字符的数量小于t中的数量，更新matched
                if (tFreq.containsKey(leftChar) && windowFreq.get(leftChar) < tFreq.get(leftChar)) {
                    matched--;
                }
                left++;  // 向右滑动左指针
            }
            right++;  // 向右滑动右指针
        }

        // 如果没有找到符合条件的子串，返回空字符串
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
    }
}
