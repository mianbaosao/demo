package org.example.suanfa.leetcode.erfen;

/**
 * @Description: 寻找旋转排序数组中的最小值、
 * @Author:bread
 * @Date: 2025-01-15 11:16
 * 这个题目还是需要画图来分析，第一个递增区间和第二个递增区间来分类，需要注意的就是判断条件
 */
public class xunzhao {
    public static int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m=(l+r)/2;
            if(nums[l]<=nums[m]&&nums[m]<=nums[r]){
                return nums[l];
            }else if(nums[l]<=nums[m]){
                l=m+1;
            }else {
                //attention！！！
                //这里为什么不是m-1是因为如果只要发生了旋转最小值肯定是会在第二个递增区间的，所以mid可能直接对应的就是最小值
                r=m;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        System.out.println(findMin(nums));
    }
}
