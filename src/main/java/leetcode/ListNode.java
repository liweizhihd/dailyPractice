package leetcode;

/**
 * @auther: liweizhi
 * Date: 2019/3/15 10:57
 * Description:
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        ListNode tmp = this;
        for (;;){
            sb.append(tmp.val);
            tmp = tmp.next;
            if(tmp == null){
                return sb.toString();
            }
            sb.append(",");
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(5);
        System.out.println(l1);
    }
}
