package org.example.suanfa.leetcode.erfen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author heweitao538 2025/6/19
 */
public class erfenTest {
    public static void main( String[] args) {
           int []nums={-1,0,1,2,-1,-4};
           List<List<Integer>> lists = find(nums);
        System.out.println(lists);;
    }

    private static List<List<Integer>> find( int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 先排序，便于去重和剪枝
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 去重
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}