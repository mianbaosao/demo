package leetcode.shu;

/**
 * @Description: 二叉树的直径
 * @Author:bread
 * 同样要考虑到递归哪里是一个难点
 * @Date: 2024-12-13 10:35
 */
public class zhijing {
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
    private static int max ;

    public static int diameterOfBinaryTree(TreeNode root) {
        max=0;
        if (root == null) return 0;
        depth(root);
        return max;
    }

    public static int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root));
    }
}
