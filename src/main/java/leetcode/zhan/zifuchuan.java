package leetcode.zhan;

import java.util.Stack;

/**
 * @Description: 字符串解码
 * @Author:bread
 * @Date: 2025-01-15 11:22
 */
public class zifuchuan {
    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                stack.pop();
                StringBuilder sb1 = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb1.append(stack.pop());

                }
                int num = Integer.parseInt(sb1.reverse().toString());
                StringBuilder sb2 = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    sb2.append(sb);
                }
                StringBuilder S3 = sb2.reverse();
                for (int j = 0; j < S3.length(); j++) {
                    stack.push(S3.charAt(j));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        System.out.println( decodeString("3[a2[bc]]"));
    }
}
