package org.example.suanfa.leetcode.shu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 验证二叉搜索树
 * @Author:bread
 * 转化为中序遍历判断顺序同样so easy
 * @Date: 2024-12-14 20:31
 */
public class yanzheng {
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
    private static List<Integer> s ;

    public static boolean isValidBST(TreeNode root) {
        s=new ArrayList<>();
        if (root == null) {
            return true;
        }
        zhong(root);
        for(int i=0;i<s.size()-1;i++){
            if(s.get(i)>=s.get(i+1)){
                return false;
            }
        }
        return true;
    }

    public static void zhong(TreeNode root) {
        if (root == null) {
            return;
        }
        zhong(root.left);
        s.add(root.val);
        zhong(root.right);
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
       /* treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);*/
        System.out.println(isValidBST(treeNode));
    }
}
