package leetcode.erfen;

/**
 * @Description: 搜索插入位置
 * @Author:bread
 * @Date: 2025-01-14 11:08
 * 这个就是标准的二分板子题
 */
public class sousuocharu {
    public static int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int m= (left+right)/2;
            if(nums[m]==target){
                return m;
            }
            if(nums[m]<target){
                left = m+1;
            }else if(nums[m]>target){
                right = m-1;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 0;
        System.out.println(searchInsert(nums,target));
    }
}
