package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 83. Remove Duplicates from Sorted List
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                ListNode delNode = node.next;
                node.next = node.next.next;
                freeNode(delNode);
            } else {
                node = node.next;
            }
        }
        return head;
    }
    private void freeNode(ListNode node) {
        node.val = 0;
        node.next = null;
    }
}
/* EOF */