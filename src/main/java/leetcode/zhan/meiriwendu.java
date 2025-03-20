package leetcode.zhan;

import java.util.Stack;

/**
 * @Description: 每日温度
 * @Author:bread
 * @Date: 2025-01-15 16:48
 */
public class meiriwendu {
    /**
     * 暴力解法
     * @param temperatures
     * @return
     */
    /*public static int[] dailyTemperatures(int[] temperatures) {
        int n=temperatures.length;
        int[] ans=new int[n];
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if(temperatures[j]>temperatures[i]){
                    ans[i]=j-i;
                    break;
                }
            }
        }
        return ans;
    }*/
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 当前温度比栈顶索引对应的温度高
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex; // 计算天数差值
            }
            // 将当前索引入栈
            stack.push(i);
        }

        // 栈中剩余的索引对应的天数默认是 0（因为它们之后没有更高温度）
        return ans;
    }
    public static void main(String[] args) {
        int[] temperatures={73,74,75,71,69,72,76,73};
        int[] ints = dailyTemperatures(temperatures);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }
    }
}
