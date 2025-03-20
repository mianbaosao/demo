package org.example.suanfa.leetcode.shuzu;

/**
 * @Description: 轮转数组
 * @Author:bread
 * @Date: 2024-12-03 20:33
 */
public class lunzhuan {
    /**
     * 记住一开始取p的时候，因为k有可能会超过n然后导致索引越界所以需要取余
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int [] n=new int[nums.length];
        int p=k%nums.length;
        int j = 0;
        for(int i=(nums.length-p);i<nums.length;i++){
            n[j]=nums[i];
            j++;
        }
        for(int i=0;i<(nums.length-p);i++){
            n[j]=nums[i];
            j++;
        }

        for(int i=0;i<nums.length;i++){
            nums[i]=n[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
