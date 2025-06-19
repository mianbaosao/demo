package org.example.suanfa.leetcode.huadongchuangkou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author heweitao538 2025/6/18
 */
public class test {
    public static void main(final String[] args) {
        System.out.println(find("abacbabc", "abc"));
    }

    public static List<Integer> find(final String s, final String t) {
        final List<Integer> res = new ArrayList<>();
        final int n1 = s.length();
        final int n2 = t.length();
        if (n1 < n2) {
            return res;
        }
        final char[] a = t.toCharArray();
        Arrays.sort(a);
        for (int i = 0; i <= n1 - n2; i++) {
            final String sub = s.substring(i, i + n2);
            final char [] b= sub.toCharArray();
            Arrays.sort(b);
            if(Arrays.equals(a,b)){
                res.add(i);
            }
        }
        return res;

    }
}
