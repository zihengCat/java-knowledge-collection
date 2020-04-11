package io.ziheng.list.leetcode;

import java.util.HashSet;
import java.util.Set;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 哈希表法
        // return detectCycleHashing(head);
        // 双指针法
        return detectCycleTwoPointer(head);
    }

    /**
     * 双指针法
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return ListNode
     */
    private ListNode detectCycleTwoPointer(ListNode head) {
        boolean hasCycle = false;
        ListNode pSlow = head;
        ListNode pFast = head;
        while (pFast != null && pFast.next != null) {
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            if (pSlow == pFast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        ListNode pA = head;
        ListNode pB = pFast;
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }

    /**
     * 哈希表法
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return ListNode
     */
    private ListNode detectCycleHashing(ListNode head) {
        Set<ListNode> aSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (aSet.contains(currentNode)) {
                return currentNode;
            }
            aSet.add(currentNode);
            currentNode = currentNode.next;
        }
        return null;
    }
}
/* EOF */