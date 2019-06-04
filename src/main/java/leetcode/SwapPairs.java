package leetcode;

import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/3/21 14:32
 * Description:
 */
public class SwapPairs {

    @Test
    public void mainMethod() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        System.out.println(listNode);
        ListNode ret = swapMine(listNode);
        System.out.println(ret);
    }

    public ListNode swapMine(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode ret = dummy;
        ListNode next;
        while (head != null && head.next != null) {
            next = head.next;
            head.next = next.next;
            next.next = head;
            dummy.next = next;
            dummy = head;
            head = head.next;
        }
        return ret.next;
    }

    /**
     * 1.找终止条件：本题终止条件很明显，当递归到链表为空或者链表只剩一个元素的时候，没得交换了，自然就终止了。
     * 2.找返回值：返回给上一层递归的值应该是已经交换完成后的子链表。
     * 3.单次的过程：因为递归是重复做一样的事情，所以从宏观上考虑，只用考虑某一步是怎么完成的。
     * 我们假设待交换的俩节点分别为head和next，next的应该接受上一级返回的子链表(参考第2步)。就相当于是一个含三个节点的链表交换前两个节点，就很简单了，想不明白的画画图就ok。
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}