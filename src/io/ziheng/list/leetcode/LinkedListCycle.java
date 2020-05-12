package io.ziheng.list.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        // return hasCycleHashing(head);
        return hasCycleTwoPointer(head);
    }
    /**
     * Linked List has Cycle -> Two Pointer
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head
     * @return boolean
     */
    private boolean hasCycleTwoPointer(ListNode head) {
        ListNode pFast = head;
        ListNode pSlow = head;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
            if (pFast == pSlow) {
                return true;
            }
        }
        return false;
    }
    /**
     * Linked List has Cycle -> Hashing
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param head
     * @return boolean
     */
    private boolean hasCycleHashing(ListNode head) {
        Set<ListNode> aSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (aSet.contains(currentNode)) {
                return true;
            }
            aSet.add(currentNode);
            currentNode = currentNode.next;
        }
        return false;
    }
}
/* EOF */