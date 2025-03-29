package org.example.suanfa.leetcode.lianbiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 排序链表
 * @Author:bread
 * @Date: 2024-12-12 11:09
 */
public class paixu {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        // 重写 toString 方法，打印链表内容
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val).append(" ");
                current = current.next;
            }
            return sb.toString().trim();
        }
    }
    public static ListNode sortList(ListNode head) {
        List<Integer> res=new ArrayList<>();
        while (head!=null){
            res.add(head.val);
            head=head.next;
        }
        Collections.sort(res);
        ListNode node=new ListNode(0);
        ListNode cur=node;
        for (Integer i:res){
            cur.next=new ListNode(i);
            cur=cur.next;
        }
        return node.next;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        System.out.println(sortList(head));
    }

}
