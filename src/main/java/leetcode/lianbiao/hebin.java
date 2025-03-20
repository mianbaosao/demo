package leetcode.lianbiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 合并两个有序链表
 * @Author:bread
 * @Date: 2024-12-11 19:26
 */
public class hebin {
    static class ListNode{
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

    /**
     * 暴力方法
     * @param list1
     * @param list2
     * @return
     */
  /*  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer>res=new ArrayList<>();
        while (list1 != null) {
            res.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            res.add(list2.val);
            list2 = list2.next;
        }
        // 对结果进行排序
        Collections.sort(res);
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < res.size(); i++) {
            cur.next = new ListNode(res.get(i));
            cur = cur.next;
        }
        return head.next;

    }*/
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode k= new ListNode(0),a=k;
        while(list1!=null&&list2!=null){
            if(list1.val < list2.val){
                a.next=list1;
                list1 =list1.next;
            }else{
                a.next=list2;
                list2 =list2.next;
            }
            a=a.next;
        }
        a.next = list1==null?list2:list1;
        return k.next;
    }
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        ListNode mergedList = mergeTwoLists(list1, list2);
        System.out.println(mergedList); // 输出: 1 1 2 3 4 4
    }
}
