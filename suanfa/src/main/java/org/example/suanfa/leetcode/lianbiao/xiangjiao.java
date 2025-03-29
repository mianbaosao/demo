package org.example.suanfa.leetcode.lianbiao;

/**
 * @Description: 相交链表
 * 考虑两个链表互相连接
 * @Author:bread
 * @Date: 2024-12-08 17:44
 */
public class xiangjiao {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
            next=null;
        }
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }

            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }
   /* public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
          pA=(pA==null)?headB:pA.next;
          pB=(pB==null)?headA:pB.next;
        }
        return pA;
    }*/
    public static void main(String[] args) {
        //1->3->5 A
        //2->4->5 B
        ListNode headA = new ListNode(1);
        ListNode headB = new ListNode(2);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        headA.next = node1;
        headB.next = node2;
        node1.next = node3;
        node2.next = node3;
        ListNode node = getIntersectionNode(headA, headB);
        System.out.println(node.val);
    }
}
