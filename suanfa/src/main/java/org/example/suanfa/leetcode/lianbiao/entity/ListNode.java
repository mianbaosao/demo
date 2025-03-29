package org.example.suanfa.leetcode.lianbiao.entity;


/**
 * @Description: 链表实体类
 * @Author:bread
 * @Date: 2024-12-08 19:34
 */
public class ListNode {
    int val;
    ListNode next;
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
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
