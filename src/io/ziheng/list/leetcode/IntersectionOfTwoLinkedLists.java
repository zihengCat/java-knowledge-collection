package io.ziheng.list.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    /**
     * Intersection of Two Linked Lists
     *
     * @param headA
     * @param headB
     * @return ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> aSet = new HashSet<>();
        ListNode currentNode = headA;
        while (currentNode != null) {
            aSet.add(currentNode);
            currentNode = currentNode.next;
        }
        currentNode = headB;
        while (currentNode != null) {
            if (aSet.contains(currentNode)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    // more elegant way
    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        int lenA = findListLength(headA);
        int lenB = findListLength(headB);
        ListNode pA = headA;
        ListNode pB = headB;
        if (lenA > lenB) {
            int diff = lenA - lenB;
            for (int i = 0; i < diff; i++) {
                pA = pA.next;
            }
        } else {
            int diff = lenB - lenA;
            for (int i = 0; i < diff; i++) {
                pB = pB.next;
            }
        }
        while (pA != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }
    private int findListLength(ListNode head) {
        int cnt = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            cnt++;
            currentNode = currentNode.next;
        }
        return cnt;
    }
}
/* EOF */