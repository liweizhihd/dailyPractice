package leetcode;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/18 18:24
 * @Description:
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        ListNode ret = addTwoNumbers(l1, l2);
        System.out.println(ret);

    }

    /**
     * Definition for singly-linked list.
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);
        ListNode itemL1 = l1;
        ListNode itemL2 = l2;
        ListNode current = ret;

        int plus = 0;

        while (itemL1 != null || itemL2 != null) {

            int v1 = itemL1 == null ? 0 : itemL1.val;
            int v2 = itemL2 == null ? 0 : itemL2.val;
            int sum = v1 + v2 + plus;
            plus = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (itemL1 != null) {
                itemL1 = itemL1.next;
            }
            if (itemL2 != null) {
                itemL2 = itemL2.next;
            }
        }

        if (plus != 0) {
            current.next = new ListNode(plus);
        }

        return ret.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
