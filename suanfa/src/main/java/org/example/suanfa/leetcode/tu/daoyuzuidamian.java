package org.example.suanfa.leetcode.tu;

/**
 * @author heweitao538 2025/7/10
 */
public class daoyuzuidamian {

    public static void main(String[] args) {
        char[][] grid ={{'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}};
        System.out.println(maxAreaOfIsland(grid));
    }

    private static int maxAreaOfIsland(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int [][]book=new int[m][n];
        return 0;
    }
}
