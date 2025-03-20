package org.example.suanfa.leetcode.dui;

/**
 * @Description: 跳跃游戏二
 * @Author:bread
 * @Date: 2025-01-17 11:52
 * 主要是需要结合上一个滑动窗口的样式找到最大值的情况，然后当i运行到最大值的位置进行跳跃到下一次窗口此时次数加1
 */
public class tiaoyue2 {
    public static int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int cur = 0, cnt = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == cur) {
                cnt++;
                cur = max;
                if (cur >= nums.length - 1) break;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int []nums={2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
