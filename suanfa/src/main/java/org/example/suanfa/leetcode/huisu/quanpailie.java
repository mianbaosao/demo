package org.example.suanfa.leetcode.huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 全排列
 * @Author:bread
 * @Date: 2024-12-18 15:42
 * 这个就是回溯板子题，按照回溯三部曲来做
 * 1.确定递归函数与参数
 * 2.设定终止条件
 * 3.遍历选择列表并回溯
 */
public class quanpailie {
    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();
    static boolean[] used;

    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        used = new boolean[nums.length];
        find(nums);
        return res;
    }

    public static void find(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            find(nums);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
