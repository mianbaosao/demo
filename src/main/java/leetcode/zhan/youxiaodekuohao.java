package leetcode.zhan;

import java.util.Stack;

/**
 * @Description: 有效的括号
 * @Author:bread
 * @Date: 2025-01-15 11:19
 */
public class youxiaodekuohao {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char []a=s.toCharArray();
        if(a.length%2!=0){
            return false;
        }
        for (char c : a) {
            if(c=='('||c=='{'||c=='['){
                stack.push(c);
            }
            if(c==')'){
                if(stack.isEmpty()||stack.pop()!='('){
                    return false;
                }
            }
            if(c=='}'){
                if(stack.isEmpty()||stack.pop()!='{'){
                    return false;
                }
            }
            if(c==']'){
                if(stack.isEmpty()||stack.pop()!='['){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
}
