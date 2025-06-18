package org.example.suanfa.leetcode.huadongchuangkou;

import java.util.HashSet;

/**
 * @author heweitao538 2025/6/18
 */
public class test {
    public static void main(final String[] args) {
        final String s="abcabcbb";
        System.out.println(find(s));
    }
    public static int find(final String s){
        final HashSet<Character> set = new HashSet<>();
        int max=0,cnt=0;
        for (int i = 0; i < s.length(); i++) {
            if(!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                cnt++;
                max=Math.max(max,cnt);
            }else{
                while (set.contains(s.charAt(i))){
                    set.remove(s.charAt(i-cnt));
                    cnt--;
                }
                set.add(s.charAt(i));
                cnt++;
                max=Math.max(max,cnt);
            }
        }
        return max;
    }
}
