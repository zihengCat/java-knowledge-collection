package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

public class ReverseLinkedList {
    /**
     * 反转单链表。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode previousNode = null;
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
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
    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        System.out.println(
            reverseLinkedList.listToString(reverseLinkedList.buildLinkedList())
        );
        System.out.println(
            reverseLinkedList.listToString(
                reverseLinkedList.reverseList(
                    reverseLinkedList.buildLinkedList()
                )
            )
        );
    }
}
/* EOF */