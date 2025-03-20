package org.example.suanfa.leetcode.tu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 腐烂的橘子
 * @Author:bread
 * @Date: 2024-12-16 19:31
 */
public class juzi {
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int row=grid.length;
        int col=grid[0].length;
        int freshCount=0;
        Queue<int []> queue=new LinkedList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1){
                    freshCount++;
                }else if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if(freshCount==0)return 0;
        int time=0;
        int []dx={0,0,1,-1};
        int []dy={-1,1,0,0};
        while(!queue.isEmpty()){
            int size=queue.size();
            Boolean hasrot=false;
            for(int i=0;i<size;i++){
                int []a=queue.poll();
                int x=a[0],y=a[1];
                for(int j=0;j<4;j++){
                    int nx=x+dx[j];
                    int ny=y+dy[j];
                    if(nx<row&&ny<col&&nx>=0&&ny>=0&&grid[nx][ny]==1){
                        grid[nx][ny]=2;
                        queue.offer(new int []{nx,ny});
                        freshCount--;
                        hasrot=true;
                    }
                }
            }
            if(hasrot){
                time++;
            }
        }
        return freshCount==0?time:-1;
    }
    public static void main(String[] args) {
        int [][]grid={{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }
}
