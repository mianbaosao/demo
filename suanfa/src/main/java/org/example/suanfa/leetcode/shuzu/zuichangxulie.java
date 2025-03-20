package org.example.suanfa.leetcode.shuzu;

import java.util.Arrays;

/**
 * @Description: 最长连续序列
 * @Author:bread
 * @Date: 2024-12-01 20:37
 */
public class zuichangxulie {
    public static int longestConsecutive(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        int cnt=1;
        int max=1;
        if(nums.length==1){
            return 1;
        }
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i+1]-1==nums[i]){
                cnt++;
            }else if(nums[i+1]==nums[i]){
                continue;
            }else{
                cnt=1;
            }
            max=Math.max(max,cnt);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {0,0    };
        System.out.println(longestConsecutive(nums));
    }
}
