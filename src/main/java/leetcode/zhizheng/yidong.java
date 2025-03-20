package leetcode.zhizheng;

/**
 * @Description: 移动零
 * @Author:bread
 * @Date: 2024-12-02 19:44
 */
public class yidong {
    public static void moveZeroes(int[] nums) {
        int r=0,l=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j =i; j < nums.length; j++) {
                if(nums[i]==0){
                    if(nums[j]!=0){
                        nums[i]=nums[j];
                        nums[j]=0;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
    }
}
