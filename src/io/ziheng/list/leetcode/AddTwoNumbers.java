package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // return addTwoNumbersIteratively(l1, l2);
        return addTwoNumbersRecursively(l1, l2);
    }
    /**
     * 递归法
     *
     * @param l1
     * @param l2
     * @return ListNode
     */
    public ListNode addTwoNumbersRecursively(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        else if (l1 == null && l2 != null) {
            return l2;
        }
        else if (l1 != null && l2 == null) {
            return l1;
        }
        int val = l1.val + l2.val;
        ListNode head = new ListNode(val % 10);
        head.next = addTwoNumbersRecursively(l1.next, l2.next);
        if (val >= 10) {
            head.next = addTwoNumbersRecursively(head.next, new ListNode(val / 10));
        }
        return head;
    }
    /**
     * 迭代法
     *
     * @param l1
     * @param l2
     * @return ListNode
     */
    public ListNode addTwoNumbersIteratively(ListNode l1, ListNode l2) {
        // 异常情况
        if (l1 == null) {
            if (l2 == null) {
                return null;
            } else {
                return l2;
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode currentNode = dummyHead;
        ListNode pA = l1;
        ListNode pB = l2;
        int carry = 0;
        while (pA != null && pB != null) {
            int val = pA.val + pB.val + carry;
            if (val >= 10) {
                carry = val / 10;
                val %= 10;
            }
            currentNode.next = new ListNode(val);
            currentNode = currentNode.next;
            pA = pA.next;
            pB = pB.next;
        }
        while (pA != null) {
            int val = pA.val + carry;
            if (val >= 10) {
                carry = val / 10;
                val %= 10;
            } else {
                carry = 0;
            }
            currentNode.next = new ListNode(val);
            currentNode = currentNode.next;
            pA = pA.next;
        }
        while (pB != null) {
            int val = pB.val + carry;
            if (val >= 10) {
                carry = val / 10;
                val %= 10;
            } else {
                carry = 0;
            }
            currentNode.next = new ListNode(val);
            currentNode = currentNode.next;
            pB = pB.next;
        }
        if (carry != 0) {
            currentNode.next = new ListNode(carry);
            currentNode = currentNode.next;
        }
        return dummyHead.next;
    }
}
/* EOF */