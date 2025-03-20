package leetcode;

import java.util.Map;
import java.util.Stack;

/**
 * @Description:
 * @Author:bread
 * @Date: 2025-02-14 14:57
 */
public class test {
     static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val=x;
        }
    }
    //   1 2 3 4
    //  4 3 2 1
    public static ListNode reverse(ListNode head){
        ListNode pre=null;
        ListNode cur=head;
        while(cur!=null){
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode a= new ListNode(1);
        ListNode b= new ListNode(2);
        ListNode c= new ListNode(3);
        ListNode d= new ListNode(4);
        a.next=b;b.next=c;c.next=d;
      ListNode res =   reverse(a);
        while (res!=null){
            System.out.print(res.val +" ");
            res=res.next;
        }
    }

}
