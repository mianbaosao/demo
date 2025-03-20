package org.example.suanfa.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 杨辉三角
 * @Author:bread
 * @Date: 2025-01-18 11:51
 *      1
 *     1 1
 *    1 2 1
 *   1 3 3 1
 *  1 4 6 4 1
 */
public class yanghui {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer>row=new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                int val=0;
                if(j==0||j==i){
                    val=1;
                }else {
                    val=ans.get(i-1).get(j)+ans.get(i-1).get(j-1);
                }
                row.add(val);
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n=5;
        List<List<Integer>> result = generate(n);

        // 输出生成的杨辉三角
        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }
}
