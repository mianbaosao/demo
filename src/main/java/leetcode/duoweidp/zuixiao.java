package leetcode.duoweidp;
/**
 * @Description: 最小路径和
 * @Author:bread
 * @Date: 2025-01-23 12:33
 * 这里需要注意的就是一开始赋值的操作和之前的求路径不同，这里需要将之前的累加起来进行
 */
public class zuixiao {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        int [][]grid={{1,3,1},{1,5,1},{4,3,1}};
        System.out.println(minPathSum(grid));
    }
}
