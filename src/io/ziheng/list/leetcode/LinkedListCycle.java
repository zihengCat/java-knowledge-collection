package io.ziheng.list.leetcode;
/**
 * LeetCode 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode pSlow = head;
        ListNode pFast = head;
        while (pFast != null && pFast.next != null) {
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            if (pSlow == pFast) {
                return true;
            }
        }
        return false;
    }
}
/* EOF */