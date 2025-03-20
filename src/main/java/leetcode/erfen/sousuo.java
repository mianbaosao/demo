package leetcode.erfen;

/**
 * @Description: 搜索旋转排序数组
 * @Author:bread
 * @Date: 2025-01-14 11:41
 * 画一个坐标轴来显示两个递增，分类讨论
 */
public class sousuo {
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            // 判断左半部分是否有序
            if (nums[l] <= nums[m]) {
                // 如果左半部分有序，判断 target 是否在左半部分
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1; // 在左半部分
                } else {
                    l = m + 1; // 否则在右半部分
                }
            } else {
                // 右半部分有序，判断 target 是否在右半部分
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1; // 在右半部分
                } else {
                    r = m - 1; // 否则在左半部分
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = search(nums, target);
        System.out.println(result); // 输出: 4
    }
}
