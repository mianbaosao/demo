package leetcode.lianbiao;

/**
 * @Description: 两数相加
 * @Author:bread
 * @Date: 2024-12-11 19:41
 */
public class liangshuxiangjia {
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, cur = dummyHead;
        int carry = 0;  // 进位初始化为0
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;  // 当前位的和，加上进位
            carry = sum / 10;  // 计算新的进位（是否有10以上）
            sum = sum % 10;  // 当前位的值
            cur.next = new ListNode(sum);  // 创建当前节点
            cur = cur.next;  // 移动cur指针到下一个节点
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if(carry>0){
            cur.next = new ListNode(carry);
        }

        return dummyHead.next;  // 返回头节点（跳过dummyHead）
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers(l1, l2);
        System.out.println(result); // 输出: 7 -> 0 -> 8
    }
}
