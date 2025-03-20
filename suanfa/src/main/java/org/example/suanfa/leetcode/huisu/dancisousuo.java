package org.example.suanfa.leetcode.huisu;

/**
 * @Description: 单词搜索
 * @Author: bread
 * @Date: 2025-01-13 10:56
 * 这里需要考虑的就是每当我搜索的时候怎么判断此时是单词的哪一位，这里就可以在dfs参数里加一个index作为此时搜索索引进而来判断
 */
public class dancisousuo {
    // 方向数组，表示上下左右四个方向
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        // 记录是否访问过
        boolean[][] visited = new boolean[rows][cols];
        // 遍历每个起点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果当前字符与目标单词的首字符匹配，开始 DFS
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, visited, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static boolean dfs(char[][] board, String word, boolean[][] visited, int x, int y, int index) {
        // 如果已经匹配完整个单词
        if (index == word.length()) {
            return true;
        }
        // 检查边界条件和访问条件
        if (!(x >= 0 && x < board.length && y >= 0 && y < board[0].length
                && !visited[x][y] && board[x][y] == word.charAt(index))) {
            return false;
        }
        // 标记当前节点为已访问
        visited[x][y] = true;
        // 遍历四个方向
        for (int k = 0; k < 4; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            if (dfs(board, word, visited, newX, newY, index + 1)) {
                return true;
            }
        }
        // 回溯：撤销访问标记
        visited[x][y] = false;
        return false;
    }
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word)); // 输出: true
    }
}
