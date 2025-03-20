package org.example.suanfa.leetcode.shuzu;

import java.util.*;

/**
 * @Description: 字母异位词分组
 * @Author:bread
 * @Date: 2024-12-01 20:25
 * 主要是要想好map的使用，主要是一个key对应一个列表，还有就是最后map.value()的使用
 */
public class yiweici {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            } else {
                map.get(key).add(str);
            }
        }
        return new ArrayList<>(map.values());

    }
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
