package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
    /**
     * 额外空间辅助检测。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param head
     * @return boolean
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }
        for (int left = 0, right = list.size() - 1; left <= right; left++, right--) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return boolean
     */
    public boolean isPalindromeTwoPointer(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        boolean isPalindrome = true;
        reverseLinkedList(head, slowPointer);
        for (ListNode i = head, j = slowPointer; j != null; i = i.next, j = j.next) {
            if (i.val != j.val) {
                isPalindrome = false;
                break;
            }
        }
        reverseLinkedList(head, slowPointer);
        return isPalindrome;
    }
    private void reverseLinkedList(ListNode head, ListNode tail) {
        ListNode currentNode = head;
        ListNode prevNode = null;
        ListNode nextNode = null;
        /**
         * 1 -> 2 -> 3 -> NULL
         */
        while (currentNode != tail) {
            nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
    }
}
/* EOF */