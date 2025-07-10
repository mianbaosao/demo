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
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        System.out.println(pathSum(root, 8));

    }

    static int cnt;
    static int t;
    private static Integer pathSum(TreeNode root, int targetSum) {
        cnt=0;
        t=targetSum;
        if(root==null){
            return 0;
        }
        dfs(root);
        return cnt;
    }
    public static void dfs(TreeNode root){
        if(root==null) {
            return;
        }
        dfsA(root,root.val);
        dfs(root.left);
        dfs(root.right);
    }

    private static void dfsA(TreeNode root, int val) {
        if(val==t){
            cnt++;
        }
        if(root.left!=null){
            dfsA(root.left,root.left.val+val);
        }
        if (root.right!=null){
            dfsA(root.right,root.right.val+val);
        }
    }


}
