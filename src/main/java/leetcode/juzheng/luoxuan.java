package leetcode.juzheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 螺旋矩阵
 * @Author:bread
 * @Date: 2024-12-08 16:52
 * 切记循环是while循环并且最后两个需要判断tb和lr
 */
public class luoxuan {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = n - 1, t = 0, b = m - 1;
        while (l <= r && t <= b) {
            for (int i = l; i <= r; i++) {
                list.add(matrix[t][i]);
            }
            t++;
            for (int i = t; i <= b; i++) {
                list.add(matrix[i][r]);
            }
            r--;
            if (t <= b) {
                for (int i = r; i >= l; i--) {
                    list.add(matrix[b][i]);
                }
                b--;
            }
            if (l <= r) {
                for (int i = b; i >= t; i--) {
                    list.add(matrix[i][l]);
                }
                l++;
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }
}
