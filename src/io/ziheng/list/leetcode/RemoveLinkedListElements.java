package io.ziheng.list.leetcode;
/**
 * Definition for singly-linked list.
 */
class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
/**
 * LeetCode 203. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {
    /**
     * Remove Linked List Elements
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        RemoveLinkedListElements removeLinkedListElements =
            new RemoveLinkedListElements();
        System.out.println(
            removeLinkedListElements.listToString(removeLinkedListElements.buildLinkedList())
        );
        System.out.println(
            removeLinkedListElements.listToString(
                removeLinkedListElements.removeElements(
                    removeLinkedListElements.buildLinkedList(),
                    2
                )
            )
        );
    }
    /**
     * Remove LinkedList elements
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        return removeElementsIteratively(head, val);
        // return removeElementsRecursively(head, val);
    }
    /**
     * Remove LinkedList elements iteratively
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head
     * @param val
     * @return ListNode
     */
    private ListNode removeElementsIteratively(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode currentNode = dummyHead;
        while (currentNode.next != null) {
            if (currentNode.next.val == val) {
                ListNode delNode = currentNode.next;
                currentNode.next = currentNode.next.next;
                // Free delNode
                delNode.val = 0;
                delNode.next = null;
            } else {
                currentNode = currentNode.next;
            }
        }
        return dummyHead.next;
    }
    /**
     * Remove LinkedList elements recursively
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head
     * @param val
     * @return ListNode
     */
    private ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode rList = removeElementsRecursively(head.next, val);
        if (head.val == val) {
            // Free delNode -> Head
            head.val = 0;
            head.next = null;
            return rList;
        }
        head.next = rList;
        return head;
    }
    public ListNode buildLinkedList() {
        ListNode head = new ListNode(-1);
        ListNode currentNode = head;
        for (int i = 0; i < 10; i++) {
            ListNode newNode = new ListNode(i);
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }
        return head;
    }
    public String listToString(ListNode head) {
        if (head == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        while (head != null) {
            stringBuilder.append(head.val);
            stringBuilder.append(", ");
            head = head.next;
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
/* EOF */