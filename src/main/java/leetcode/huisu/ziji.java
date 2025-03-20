package leetcode.huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 子集
 * @Author:bread
 * @Date: 2024-12-18 16:25
 * 按照回溯三部曲思考，最重要的就是想到start 其次重要的就是 递归结束条件
 */
public class ziji {
    static List<List<Integer>> result;
    static LinkedList<Integer> path;

    public static List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        path = new LinkedList<>();
        subsetsHelper(nums, 0);
        return result;
    }

    private static void subsetsHelper(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsHelper(nums, i + 1);
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
}
