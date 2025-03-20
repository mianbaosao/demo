package org.example.suanfa.leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 组合总和
 * @Author:bread
 * @Date: 2025-01-13 10:36
 * 这里需要强调的就是这个递归条件的判断，不能弄成sum==target不知道为啥就会编译不过去。得切换一下思路sum+c[i]
 */
public class zuhezonghe {

    public static List<List<Integer>> res= null;
    public static List<Integer> list = null;
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        dfs(candidates,target,0,0);
        return res;
    }
    public static void dfs(int[] candidates,int target,int index,int sum){
        if(target==sum){
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i < candidates.length; i++) {
            if(sum+candidates[i]<=target){
                list.add(candidates[i]);
                dfs(candidates,target,i,sum+candidates[i]);
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        combinationSum(candidates,7);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i)+" ");
        }
    }
}
