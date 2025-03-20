package leetcode.dp;

/**
 * @Description: 打家劫舍
 * @Author:bread
 * @Date: 2025-01-18 12:04
 * dp[i] 表示抢劫到第 i 个房屋时能够获得的最大金额。
 */
public class dajiajieshe {

    public static int rob(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        int []dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int []num={2,1,1,2};
        System.out.println(rob(num));
    }
}
