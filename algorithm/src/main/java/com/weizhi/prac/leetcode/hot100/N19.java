package com.weizhi.prac.leetcode.hot100;


public class N19 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        N19 obj = new N19();
        System.out.println(obj.doGetSize(node));
    }

    // 快慢指针
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // remove
        second.next = second.next.next;
        return dummy.next;
    }

    // 先计算长度
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = doGetSize(head);
        int rmIdx = size - n;
        ListNode pre = new ListNode(0, head);
        ListNode cursor = pre;
        for (int i = 0; i < rmIdx; i++) {
            cursor = cursor.next;
        }
        // remove cursor
        cursor.next = cursor.next.next;
        return pre.next;
    }

    public int doGetSize(ListNode node) {
        int size = 0;
        ListNode cursor = node;
        while (cursor != null) {
            size++;
            cursor = cursor.next;
        }
        return size;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
