package org.example.suanfa.leetcode.dp;

/**
 * 乘积最大子数组
 */
public class zuidazi {
    public static void main(String[] args) {
        int [] nums={-2,3,-4};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }
}
