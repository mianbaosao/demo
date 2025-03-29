package org.example.suanfa.leetcode.lianbiao;

/**
 * @Description: 删除链表的倒数第N个节点
 * @Author:bread
 * @Date: 2024-12-11 20:10
 */
public class shanchu {
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
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("原始链表: " + head);
        head = removeNthFromEnd(head, 2);
        System.out.println("删除倒数第2个节点后的链表: " + head);
    }
}
