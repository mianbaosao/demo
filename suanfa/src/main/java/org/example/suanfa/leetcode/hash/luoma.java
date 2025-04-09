package org.example.suanfa.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 整数转罗马数字
 * 罗马转整数
 */
public class luoma {
    public static void main(String[] args) {
        int a=3749;
        System.out.println(intToRoman(a));
        String p="LVIII";
        System.out.println(romanToInt(p));
    }
    public static String intToRoman(int num) {
        if (num <= 0 || num > 3999) {
            throw new IllegalArgumentException("只能转换 1 到 3999 之间的数字");
        }

        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9,   5,  4,  1};
        String[] romans = {"M",  "CM","D", "CD", "C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                result.append(romans[i]);
                num -= values[i];
            }
        }

        return result.toString();
    }
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<String,Integer> map = new HashMap<>();
        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9,   5,  4,  1};
        String[] romans = {"M",  "CM","D", "CD", "C","XC","L","XL","X","IX","V","IV","I"};

        for (int i = 0; i < values.length; i++) {
            map.put(romans[i], values[i]);
        }
        int result = 0;
        int a=0;
       while (a < s.length()) {
           if(a+1<s.length()) {
               if(map.containsKey(s.substring(a, a + 2))) {
                   result += (map.get(s.substring(a, a + 2)));
                   a += 2;
                   continue;
               }
           }
           result += map.get(s.substring(a,a+1));
             a++;
         }
        return result;
    }

}
