package org.example.suanfa.leetcode.shu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二叉树展开为链表
 * @Author:bread
 * @Date: 2024-12-14 20:38
 */
public class zhankai {
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
    private static List<TreeNode>res;
    public static void flatten(TreeNode root) {
        res=new ArrayList<>();
        dfs(root);
        for (int i = 0; i < res.size()-1; i++) {
            res.get(i).left=null;
            res.get(i).right=res.get(i+1);
        }
    }
    public static void dfs(TreeNode root){
        if (root==null){
            return;
        }
        res.add(root);
        dfs(root.left);
        dfs(root.right);
    }
    public static void main(String[] args) {
    flatten(new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(5,new TreeNode(6),new TreeNode(7))));
    }
}
