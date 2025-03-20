package leetcode.lianbiao;

import java.util.Stack;

/**
 * @Description: 回文链表
 * @Author:bread
 * @Date: 2024-12-08 20:14
 */
public class huiwen {
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

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // 空链表或者只有一个元素是回文
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static void main(String[] args) {
        // 创建一个链表 1 -> 2 -> 3 -> 2 -> 1
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);
        /*head.next.next.next = new ListNode(1);*/
        System.out.println(head); // 输出：1 2 3 2 1
        System.out.println(isPalindrome(head)); // 输出：true

    }
}
