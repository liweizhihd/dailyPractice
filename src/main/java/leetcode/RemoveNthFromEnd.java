package leetcode;

/**
 * @auther: liweizhi
 * Date: 2019/3/15 10:56
 * Description:给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode listNode = removeNthFromEnd002(head, 3);
        System.out.println(listNode);
    }

    public static ListNode removeNthFromEnd002(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        ListNode temp = second.next;
        second.next = second.next.next;
        temp.next = null;
        temp = null;
        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = head;
        int length = 0;
        while (first != null) {
            first = first.next;
            length++;
        }

        int target = length - n;
        first = dummy;
        while (target > 0) {
            target--;
            first = first.next;
        }

        ListNode temp = first.next;
        first.next = first.next.next;
        temp.next = null;
        temp = null;

        return dummy.next;
    }

}
