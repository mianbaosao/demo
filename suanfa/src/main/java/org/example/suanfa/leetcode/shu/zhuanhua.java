package org.example.suanfa.leetcode.shu;

/**
 * @Description: 有序数组转化为二叉搜索树
 * @Author:bread
 * 主要是递归哪里考虑到了就可以
 * @Date: 2024-12-13 16:19
 */
public class zhuanhua {
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
    public static TreeNode sortedArrayToBST(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    public static TreeNode find(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (r+l) / 2;
        TreeNode res = new TreeNode(nums[mid]);
        res.left = find(nums, l, mid - 1);
        res.right = find(nums, mid + 1, r);
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(root.val);
    }
}
