package org.example.suanfa.leetcode.lianbiao;

/**
 * @author heweitao538 2025/6/24
 */
public class ListNodeTest {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
            next=null;
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
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println("原始链表: " + head);
        ListNode result = swapPairs(head);
        System.out.println("交换后的链表: " + result);
    }

    private static ListNode swapPairs(ListNode head) {
        //1 2 3 4
        ListNode dum=new ListNode(0);
        dum.next=head;
        ListNode cur=dum;
        while(cur.next!=null&&cur.next.next!=null){
            ListNode first=cur.next;
            ListNode second=cur.next.next;
            cur.next=second;
            first.next=second.next;
            second.next=first;
            cur=first;
        }
        return dum.next;
    }


}
