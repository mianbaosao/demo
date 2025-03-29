package org.example.suanfa.leetcode.lianbiao;

/**
 * @Description: 环形链表2
 * @Author:bread
 * @Date: 2024-12-11 19:17
 */
public class huanxin2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        // 重写 toString 方法，打印链表内容
       /* @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
           ListNode current = this;
            while (current != null) {
                sb.append(current.val).append(" ");
                current = current.next;
            }
            return sb.toString().trim();
        }*/
    }
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;  // 无环情况的提前返回
        }
        ListNode slow = head, fast = head;
        // 第一部分：快慢指针相遇
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {  // 相遇了，表示链表有环
                break;
            }
        }
        // 第二部分：链表无环
        if (fast == null || fast.next == null) {
            return null;  // 没有环
        }
        // 第三部分：找环的起始节点
        slow = head;  // 将 slow 重置为头部
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;  // 环的起始节点
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(detectCycle(head));
    }
}
