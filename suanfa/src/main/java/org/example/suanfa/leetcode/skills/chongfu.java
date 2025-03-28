package org.example.suanfa.leetcode.skills;

import java.util.HashMap;
import java.util.Map;

/**
 * 唯一重复数
 */
public class chongfu {
    public static void main(String[] args) {
        int []a={1,2,3,4,2};
        System.out.println(findDuplicate(a));
    }
    public static int findDuplicate(int[] nums) {
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            if(map.containsKey(nums[i])){
                return nums[i];
        }else{
                map.put(nums[i],i);
    }
        return -1;
}
}
