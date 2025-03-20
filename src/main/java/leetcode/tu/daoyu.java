package leetcode.tu;

/**
 * @Description: 岛屿数量
 * @Author:bread
 * @Date: 2024-12-16 19:13
 */
public class daoyu {
    private static int count;
    public static int numIslands(char[][] grid) {
        count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(char[][]grid ,int i,int j){
        int [][]dp=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]=='0'||grid[i][j]=='2') return;
        grid[i][j]='2';
        for (int i1 = 0; i1 < 4; i1++) {
            dfs(grid,i+dp[i1][0],j+dp[i1][1]);
        }
    }


    public static void main(String[] args) {
        char[][] grid ={{'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }
}
