package net.canway.rabbitmq;

import org.junit.jupiter.api.Test;

/**
 * @author eltons,  Date on 2018-11-24.
 */
public class Solution2 {
    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = new S().addTwoNumbers(l1, l2);
        System.out.println(res.val);
    }
}

//链表相加
class S {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode top = l3;
        int add = 0;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                l3.val = l2.val + add;
                l3.next = l2.next;
                return top;

            } else if (l2 == null) {
                l3.val = l1.val + add;
                l3.next = l1.next;
                return top;
            } else {
                int temp = 0;
                if ((temp = l1.val + l2.val) >= 10) {
                    l3.val = temp % 10 + add;
                    add = 1;
                } else {
                    l3.val = temp + add;
                    add = 0;
                }
                l3.next = new ListNode();
                l3 = l3.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if (add > 0) {
            l3.next = new ListNode(1);
        }
        return top;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode l) {
        val = x;
        next = l;
    }

    ListNode() {
    }
}