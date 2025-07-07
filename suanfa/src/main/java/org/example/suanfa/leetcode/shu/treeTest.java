package org.example.suanfa.leetcode.shu;

/**
 * @author heweitao538 2025/7/2
 */
public class treeTest {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root));
    }
    static int max;
    public static int diameterOfBinaryTree(TreeNode root) {
         max=Integer.MIN_VALUE;
        if(root==null){
            return 0;
        }
        dfs(root);
        return max;
    }
    public static int dfs(TreeNode root) {
        if (root ==null){
            return 0;
        }
        int l=dfs(root.left);
        int r=dfs(root.right);
        max=Math.max(max,l+r);
        return Math.max(l,r)+1;
    }

}
