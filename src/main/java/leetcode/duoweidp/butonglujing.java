package leetcode.duoweidp;

/**
 * @Description: 不同路径
 * @Author:bread
 * @Date: 2025-01-23 12:28
 * 多维dp需要注意的就是一开始对二维数组的行和列进行初始赋值
 */
public class butonglujing {
    public static int uniquePaths(int m, int n) {
        int [][]dp=new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;  // 只能往下走，所以路径数为1
        }
        // 初始化第一列的路径数
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;  // 只能往右走，所以路径数为1
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int m=3,n=7;
        System.out.println(uniquePaths(m,n));
    }
}
