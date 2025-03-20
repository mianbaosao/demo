package leetcode.shu;

import leetcode.shu.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 中序遍历
 * @Author:bread
 * @Date: 2024-12-12 11:26
 */
public class zhongxu {
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
    private static List<Integer> res = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        zhong(root);
        return res;
    }
    public static void zhong(TreeNode root){
        if (root == null) return;
        zhong(root.left);
        res.add(root.val);
        zhong(root.right);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(inorderTraversal(treeNode));
    }
}
