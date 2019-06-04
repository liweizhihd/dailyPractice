package leetcode;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/3/19 16:01
 * Description:合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 */
public class MergeKLists {
    public static void main(String[] args) {

    }

    @Test
    public void mainMethod() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[]{l1, l2, l3};

        System.out.println(mergeKLists(new ListNode[1]));

        /*System.out.println(getMinListNode(listNodes).val);
        for (ListNode item : listNodes) {
            System.out.println(item);
        }*/
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode ret = dummy;
        int validCount = lists.length;
        ListNode min;
        while (validCount > 0) {
            min = getMinListNode(lists);
            if (min == null) {
                break;
            }
            dummy.next = min;
            dummy = min;
            min = min.next;
            if (min == null) {
                validCount--;
            }
        }
        return ret.next;
    }

    private ListNode getMinListNode(ListNode[] lists) {
        ListNode ret = null;
        ListNode item;
        int minIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            item = lists[i];
            if (item == null) {
                continue;
            }
            if (ret == null) {
                ret = lists[i];
                minIndex = i;
                continue;
            }
            if (ret.val > item.val) {
                ret = item;
                minIndex = i;
            }
        }
        if (minIndex != -1) {
            lists[minIndex] = lists[minIndex].next;
        }
        return ret;
    }

}
