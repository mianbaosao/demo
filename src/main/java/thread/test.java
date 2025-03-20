package thread;

import java.util.Stack;

/**
 * @Description:
 * @Author:bread
 * @Date: 2025-02-14 14:49
 */
public class test {
    public static boolean find(String s){
        Stack<Character> stack=new Stack<>();
        for (char c : s.toCharArray()) {
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else{
                char top =stack.pop();
                if((c==')'&&top!='('||c==']'&&top!='['||c=='}'&&top!='{')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String s= "('，')'，'{'，'}'，'['，']";
        System.out.println(find(s));
    }
}
