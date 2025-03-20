package leetcode.zichuan;

/**
 * @Description: 和为k的子数组
 * @Author:bread
 * @Date: 2024-12-03 19:44
 */
public class zishuzu {
    public static int subarraySum(int[] nums, int k) {
        int count = 0,s=0;
        for (int i = 0; i < nums.length; i++) {
            s=0;
            for (int j = i; j < nums.length; j++) {
                s+=nums[j];
                if (s==k){
                    count++;
                }
            }
        }
        return count;

    }
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(subarraySum(nums,k));
    }
}
