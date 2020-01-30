package io.ziheng.leetcode;
/**
 * LeetCode 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {
    /**
     * Definition for singly-linked list.
     */
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /**
     * Recursive way.
     */
    public ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode resultList = removeElementsRecursively(head.next, val);
        if (head.val == val) {
            return resultList;
        } else {
            head.next = resultList;
            return head;
        }
    }
    /**
     * Iterative way.
     */
    public ListNode removeElementsIteratively(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode previousNode = dummyHead;
        while (previousNode.next != null) {
            if (previousNode.next.val == val) {
                //ListNode deleteNode = previousNode.next;
                previousNode.next = previousNode.next.next;
                //freeNode(deleteNode);
            } else {
                previousNode = previousNode.next;
            }
        }
        return dummyHead.next;
    }
    private void freeNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = 0;
        node.next = null;
    }
}
/* EOF */