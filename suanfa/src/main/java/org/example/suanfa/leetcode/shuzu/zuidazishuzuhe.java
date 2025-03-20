package org.example.suanfa.org.example.suanfa.leetcode.shuzu;


/**
 * @Description: 最大子数组和
 * @Author:bread
 * @Date: 2025-02-25 18:29
 * 这里的区间和主要要记住一个点就是当s小于0之后直接赋值为0，还有就是一开始max的赋值要很小，要考虑到都是负数的情况
 */
public class zuidazishuzuhe {
     public static int maxSubArray(int[] nums) {
         int max = -999999, s = 0;
         for (int i = 0; i < nums.length; i++) {
             s += nums[i];
             max = Math.max(max, s);
             if (s < 0) {
                 s = 0;
             }
         }
         return max;
    }

    public static void main(String[] args) {
        int []num={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(num));
    }
}
