package leetcode.lianbiao;

/**
 * @Description: 反转链表
 * @Author:bread
 * @Date: 2024-12-08 19:56
 */
public class fanzhuan {
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

    public static ListNode reverseList(ListNode head) {
        ListNode pre=null;
        while (head!=null){
            ListNode temp=head.next;
            head.next=pre;
            pre=head;
            head=temp;
        }
        return pre;
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
}
