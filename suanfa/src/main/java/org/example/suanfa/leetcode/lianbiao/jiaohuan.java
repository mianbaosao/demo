package org.example.suanfa.leetcode.lianbiao;

/**
 * @Description: 两两交换链表节点
 * @Author:bread
 * @Date: 2024-12-11 20:38
 */
public class jiaohuan {
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

    public static ListNode swapPairs(ListNode head) {
        ListNode dum= new ListNode(0);
        dum.next = head;
        ListNode temp=dum;
        while (temp.next != null && temp.next.next != null){
            ListNode first = temp.next,second = temp.next.next;
            first.next = second.next;
            second.next = first;
            temp.next = second;


            temp = temp.next.next;
            first = first.next;
            if (first != null){
                second = first.next;
            }
        }
        return dum.next;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println("原始链表: " + head);
        ListNode result = swapPairs(head);
        System.out.println("交换后的链表: " + result);
    }
}
