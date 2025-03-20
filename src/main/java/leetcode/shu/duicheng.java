package leetcode.shu;

import java.util.HashMap;

/**
 * @Description:对称
 * @Author:bread
 * @Date: 2024-12-12 15:53
 */
public class duicheng {
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
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    private static boolean isMirror(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        if (root.val != root1.val) {
            return false;
        }
        return isMirror(root.left, root1.right) && isMirror(root.right, root1.left);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        HashMap<Integer,String>map =new HashMap<>();
        root.left = left;
        root.right = right;
        System.out.println(isSymmetric(root));
    }


}
