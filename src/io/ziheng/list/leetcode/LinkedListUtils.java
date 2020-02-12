package io.ziheng.list.leetcode;
import io.ziheng.list.leetcode.ListNode;
public class LinkedListUtils {
    public static ListNode buildLinkedList() {
        return buildLinkedList(8);
    }
    public static ListNode buildLinkedList(int n) {
        ListNode head = new ListNode(-1);
        ListNode currentNode = head;
        for (int i = 0; i < n; i++) {
            ListNode newNode = new ListNode(i);
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }
        return head;
    }
    public static String listToString(ListNode head) {
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