package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCodde 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode currentNode = dummyHead;
        ListNode pA = l1;
        ListNode pB = l2;
        while (pA != null && pB != null) {
            if (pA.val < pB.val) {
                currentNode.next = pA;
                pA = pA.next;
            } else {
                currentNode.next = pB;
                pB = pB.next;
            }
            currentNode = currentNode.next;
        }
        while (pA != null) {
            currentNode.next = pA;
            pA = pA.next;
            currentNode = currentNode.next;
        }
        while (pB != null) {
            currentNode.next = pB;
            pB = pB.next;
            currentNode = currentNode.next;
        }
        return dummyHead.next;
    }
}
/* EOF */