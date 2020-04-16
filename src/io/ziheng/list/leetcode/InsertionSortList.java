package io.ziheng.list.leetcode;

import io.ziheng.list.leetcode.ListNode;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * LeetCode 147. Insertion Sort List
 * https://leetcode.com/problems/insertion-sort-list/
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        List<Integer> aList = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            aList.add(currentNode.val);
            currentNode = currentNode.next;
        }
        Collections.sort(aList);
        currentNode = head;
        int index = 0;
        while (currentNode != null) {
            currentNode.val = aList.get(index);
            currentNode = currentNode.next;
            index++;
        }
        return head;
    }
}
/* EOF */