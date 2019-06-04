package leetcode;

/**
 * @auther: liweizhi
 * Date: 2019/3/16 11:38
 * Description:将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(5);
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        ListNode listNode = mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
    }

    // 1 3 5 5
    // 2 4 6
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode ret = new ListNode(0);
        ListNode dummy = ret;
        //dummy.next = ret;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ret.next = l2;
                ret = ret.next;
                l2 = l2.next;
            } else {
                ret.next = l1;
                ret = ret.next;
                l1 = l1.next;
            }
        }
        if (l1 == null) {
            ret.next = l2;
        } else if (l2 == null) {
            ret.next = l1;
        }
        return dummy.next;
    }
}
