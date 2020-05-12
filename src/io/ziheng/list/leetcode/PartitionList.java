package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 86. Partition List
 * https://leetcode.com/problems/partition-list/
 */
public class PartitionList {
    /**
     * Partition Linked List
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head
     * @param x
     * @return ListNode
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode pLess = new ListNode(0);
        ListNode pGreater = new ListNode(0);
        ListNode pLessHead = pLess;
        ListNode pGreaterHead = pGreater;
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            if (currentNode.val < x) {
                currentNode.next = null;
                pLess.next = currentNode;
                pLess = currentNode;
            } else {
                currentNode.next = null;
                pGreater.next = currentNode;
                pGreater = currentNode;
            }
            currentNode = nextNode;
        }
        pLess.next = pGreaterHead.next;
        return pLessHead.next;
    }
}
/* EOF */