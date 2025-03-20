package leetcode.erfen;

/**
 * @Description: 搜索二维矩阵
 * @Author:bread
 * @Date: 2025-01-14 11:12
 * 这个题目暴力就行不用二分
 */
public class sousuoerweijuzhneg {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row=matrix.length;
        int col=matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0]<=target&&matrix[i][col-1]>=target){
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j]==target){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(searchMatrix(matrix, 5));
    }
}
