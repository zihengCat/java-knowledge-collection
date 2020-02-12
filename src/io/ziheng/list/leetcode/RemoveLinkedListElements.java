package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        return removeElementsV1(head, val);
        //return removeElementsV2(head, val);
    }
    /**
     * 移除链表元素。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1) 
     *
     * @param head
     * @param val
     * @return ListNode
     */
    public ListNode removeElementsV1(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode currentNode = dummyHead;
        while (currentNode.next != null) {
            if (currentNode.next.val == val) {
                ListNode delNode = currentNode.next;
                currentNode.next = currentNode.next.next;
                // Delete Node
                delNode.val = -1;
                delNode.next = null;
            } else {
                currentNode = currentNode.next;
            }
        }
        return dummyHead.next;
    }
    /**
     * 移除链表元素（递归）。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) 
     *
     * @param head
     * @param val
     * @return ListNode
     */
    public ListNode removeElementsV2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode resultList = removeElementsV2(head.next, val);
        if (head.val == val) {
            return resultList;
        } else {
            head.next = resultList;
            return head;
        }
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
        RemoveLinkedListElements removeLinkedListElements =
            new RemoveLinkedListElements();
        System.out.println(
            removeLinkedListElements.listToString(removeLinkedListElements.buildLinkedList())
        );
        System.out.println(
            removeLinkedListElements.listToString(
                removeLinkedListElements.removeElementsV1(
                    removeLinkedListElements.buildLinkedList(),
                    2
                )
            )
        );
    }
}
/* EOF */