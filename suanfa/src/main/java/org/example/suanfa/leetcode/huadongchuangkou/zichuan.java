package org.example.suanfa.leetcode.huadongchuangkou;
import java.util.HashSet;


/**
 * @Description: 无重复字符的最长字串
 * @Author:bread
 * @Date: 2024-12-02 21:44
 * 切记用hashmap以及用一个start索引来求最大
 */
public class zichuan {

    /**
     * hashset解决
     * @param s
     * @return
     */
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
  /*  public static int lengthOfLongestSubstring(String s) {
        char[] a = s.toCharArray();  // 转为字符数组
        int cnt = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i]) && map.get(a[i]) >= start) {
                start = map.get(a[i]) + 1;
            }
            // 更新当前字符的索引
            map.put(a[i], i);
            // 更新当前无重复子串的长度
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
*/



    public static void main(final String[] args) {
        final String s = "dvdf";
        System.out.println(find(s));
    }
}
