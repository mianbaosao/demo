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
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        System.out.println(node1.next.val+"  " +node1.val);
        System.out.println(reverseList(node1));
    }

    private static ListNode reverseList(ListNode node1) {
        ListNode head=null;
        while (node1!=null) {
            ListNode temp = node1.next;
            node1.next = head;
            head = node1;
            node1 = temp;
        }
        return head;
    }


}
