package org.example.suanfa.leetcode.shu;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        TreeNode root = new TreeNode(1);                           //1
        root.left = new TreeNode(2);                             //2  3
        root.right = new TreeNode(3);                           //4  5  6
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        List<List<Integer>> res=levelOrder(root);
        System.out.println(res);

    }
    static List<List<Integer>> res;
    private static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        res = new LinkedList<>();
        if(root==null){
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> path=new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode a=queue.poll();
                if(a.left!=null){
                    queue.add(a.left);
                }
                if(a.right!=null){
                    queue.add(a.right);
                }
                path.add(a.val);
            }
            res.add(path);
        }
        return res;

    }


}
