package org.example.suanfa.leetcode.erfen;

/**
 * @author heweitao538 2025/6/19
 */
public class erfenTest {
    public static void main( String[] args) {
           int []nums={4,5,6,7,0,1,2};
           int []nums1={5,7,7,8,8,10};
           int target=0;
           int target1=6;
           int res=find(nums,target);
        System.out.println(res);
    }

    private static int  find( int[] nums,int target) {
            int left=0,right=nums.length-1,mid=0;
        while(left<=right){
            mid=(left+right)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[left]<=nums[mid]){
                if(nums[left]<=target&&target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]< target && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}