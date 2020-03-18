package io.ziheng.list.leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode currentNode = headA;
        while (currentNode != null) {
            set.add(currentNode);
            currentNode = currentNode.next;
        }
        currentNode = headB;
        while (currentNode != null) {
            if (set.contains(currentNode)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}
/* EOF */