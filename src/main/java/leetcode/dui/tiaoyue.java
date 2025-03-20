package leetcode.dui;

/**
 * @Description: 跳跃游戏
 * @Author:bread
 * @Date: 2025-01-17 11:44
 * 就是很简单的问题，可以模拟窗口来看，主要是记得i和坐标之间的联系
 */
public class tiaoyue {
    public static boolean canJump(int[] nums) {
        int len=nums.length;
        int max=0;
        for (int i = 0; i < len; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
            if (max >= len - 1) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }
}
