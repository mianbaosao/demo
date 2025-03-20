package org.example.suanfa.leetcode.shu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉树的最大深度
 * @Author:bread
 * 层序遍历so easy
 * @Date: 2024-12-12 11:37
 */
public class zuidachengdu {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static int cnt,cnt2;
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int cnt=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth(root));
    }
}
