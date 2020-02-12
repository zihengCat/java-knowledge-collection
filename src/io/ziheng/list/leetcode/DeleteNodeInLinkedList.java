package io.ziheng.list.leetcode;
import io.ziheng.list.leetcode.ListNode;
/**
 * LeetCode 237. Delete Node in a Linked List
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        /**
         * 需要考虑尾节点的情况。
         * 注：题意保证传入节点不会是尾节点。
         */
        if (node.next == null) {
            node = null;
            return;
        }
        /**
         * 删除逻辑：
         * 1. 使用当前节点的 Next 节点的值 Value 覆盖当前节点。
         * 2. 删除 Next 节点。
         */
        node.val = node.next.val;
        ListNode delNode = node.next;
        node.next = node.next.next;
        // Delete Node
        delNode.val = -1;
        delNode.next = null;
    }
}
/* EOF */