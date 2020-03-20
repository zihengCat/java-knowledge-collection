package io.ziheng.list.leetcode;

/**
 * LeetCode 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prevNode = dummyHead;
        for (int i = 0; i < m - 1; i++) {
            prevNode = prevNode.next;
        }
        ListNode currentNode = prevNode.next;
        ListNode nextNode = currentNode.next;
        // >>> prev = 1, curr = 2, next = 3
        // 1 -> 2 -> 3 -> 4 -> 5 -> NULL; m=2, n=4 
        // dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> NULL
        for (int i = 0; i < n - m; i++) {
            currentNode.next = nextNode.next;
            nextNode.next = prevNode.next;
            prevNode.next = nextNode;
            nextNode = currentNode.next;
        }
        return dummyHead.next;
    }
}
/* EOF */