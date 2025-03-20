package org.example.suanfa.leetcode.shuzu;

import java.util.Arrays;

/**
 * @Description: 合并区间（贪心）
 * @Author:bread
 * @Date: 2024-12-03 20:19
 * 主要记住排序方式和idx
 */
public class hebing {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);

    }
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println( merge(intervals));
    }
}
