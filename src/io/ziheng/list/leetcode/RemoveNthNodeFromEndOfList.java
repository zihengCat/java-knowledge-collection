package io.ziheng.list.leetcode;
import io.ziheng.list.leetcode.ListNode;
import io.ziheng.list.leetcode.LinkedListUtils;
/**
 * LeetCode 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * 计算链表长度，倒数转正数。
     * 时间复杂度：O(2n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @param n
     * @return ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * List: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
         * n: 2
         * Result: 1 -> 2 -> 3 -> 5 -> NULL
         */
        int listLength = findListLength(head);
        int delIndex = listLength - n + 1;
        head = removeNode(head, delIndex);
        return head;
    }
    public static ListNode removeNode(ListNode head, int index) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode previousNode = dummyHead;
        ListNode currentNode = head;
        for (int i = 1; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode.next = currentNode.next;
        // Delete Node
        currentNode.val = -1;
        currentNode.next = null;
        return dummyHead.next;
    }
    public static int findListLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int cnt = 0;
        while (head != null) {
            cnt++;
            head = head.next;
        }
        return cnt;
    }
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.buildLinkedList();
        System.out.println(
            LinkedListUtils.listToString(head)
        );
        new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 2);
        System.out.println(
            LinkedListUtils.listToString(head)
        );
    }
}
/* EOF */