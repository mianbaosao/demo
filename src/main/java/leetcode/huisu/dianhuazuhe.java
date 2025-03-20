package leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 电话号码的字母组合
 * @Author:bread
 * @Date: 2024-12-18 16:42
 * 这个主要是要注意一开始定义好nmsString的9个数字
 */
public class dianhuazuhe {
   static  List<String> list ;
    public static List<String> letterCombinations(String digits) {
        list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, numString, 0);
        return list;
    }
    static StringBuilder temp = new StringBuilder();
    public static void backTracking(String digits, String[] numString, int num) {
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking(digits, numString, num + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
    public static void main(String[] args) {
        letterCombinations("23");
        System.out.println(list);
    }
}
