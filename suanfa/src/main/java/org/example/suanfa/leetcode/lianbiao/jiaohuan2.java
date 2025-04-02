package org.example.suanfa.leetcode.lianbiao;

/**
 * 每隔k个链表进行交换
 */
public class jiaohuan2 {
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

    public static void main(String[] args) {
       ListNode a= new ListNode(1);
       a.next = new ListNode(2);
       a.next.next = new ListNode(3);
       a.next.next.next = new ListNode(4);
       a.next.next.next.next = new ListNode(5);
       ListNode b=reverseKGroup(a,3);
       while (b != null) {
           System.out.print(b.val+" ");
           b=b.next;
       }
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, pre = dummy, nex = dummy;
        int count = 0;
        // 统计链表的节点总数
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }
        // 逐组反转
        while (count >= k) {
            cur = pre.next;
            nex = cur.next;
            for (int i = 1; i < k; i++) {
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            count -= k;
        }
        return dummy.next;
    }
}
