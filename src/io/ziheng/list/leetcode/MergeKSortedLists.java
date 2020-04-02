package io.ziheng.list.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.ziheng.list.leetcode.ListNode;

/**
 * LeetCode 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        List<Integer> valuesList = new LinkedList<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                valuesList.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(valuesList);
        ListNode dummyHead = new ListNode(-1);
        ListNode currentNode = dummyHead;
        for (Integer val : valuesList) {
            ListNode newNode = new ListNode(val);
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }
        return dummyHead.next;
    }
}
/* EOF */