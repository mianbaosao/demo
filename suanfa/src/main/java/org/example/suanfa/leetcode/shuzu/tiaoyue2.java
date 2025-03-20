package org.example.suanfa.leetcode.shuzu;

/**
 * @Description: 跳跃游戏2
 * @Author:bread
 * @Date: 2024-12-01 19:48
 */
public class tiaoyue2 {
    public static int jump(int[] nums) {
        int count = 0;
        int max = 0;
        int s=0;
        for (int i = 0; i < nums.length-1; i++) {
            max=Math.max(max,nums[i]+i);
            if(i==s){
                count++;
                s=max;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
