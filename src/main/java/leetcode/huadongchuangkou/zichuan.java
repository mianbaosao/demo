package leetcode.huadongchuangkou;
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
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;  // 最大长度
        HashSet<Character> set = new HashSet<>();  // 用来存储当前窗口的字符
        int left = 0;  // 窗口的左边界
        for (int right = 0; right < s.length(); right++) {
            // 如果当前字符在窗口内，滑动左边界
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));  // 移除左边界的字符
                left++;  // 向右移动左边界
            }

            // 将当前字符加入窗口
            set.add(s.charAt(right));

            // 更新最大长度
            max = Math.max(max, right - left + 1);
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



    public static void main(String[] args) {
        String s = "dvdf";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
