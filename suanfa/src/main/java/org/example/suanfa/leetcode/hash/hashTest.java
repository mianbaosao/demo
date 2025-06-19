package org.example.suanfa.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heweitao538 2025/6/19
 */
public class hashTest {
    public static void main(final String[] args) {
        final String []s={"eat", "tea", "tan", "ate", "nat", "bat"};
        final List<List<String>> lists = find(s);
        System.out.println(lists);
    }

    private static List<List<String>> find(final String[] s) {
        final Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            final char[] a = s[i].toCharArray();
            Arrays.sort(a);
            final String s1 = new String(a);
            if (map.containsKey(s1)) {
                map.get(s1).add(s[i]);
            } else {
                map.put(s1, new ArrayList<>());
                map.get(s1).add(s[i]);
            }
        }
        return new ArrayList<>(map.values());
    }
}
