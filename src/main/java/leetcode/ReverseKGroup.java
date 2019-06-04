package leetcode;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/3/22 11:15
 * Description: https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 */
public class ReverseKGroup {

    @Test
    public void mainMethod() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        System.out.println(reverseKGroup(listNode, 1));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode[] arr = new ListNode[k];
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null) {
                return head;
            }
            arr[i] = check;
            check = check.next;
        }
        arr[0].next = reverseKGroup(arr[k - 1].next, k);
        for (int i = 1; i < k; i++) {
            arr[i].next = arr[i - 1];
        }
        return arr[k - 1];
    }
}
