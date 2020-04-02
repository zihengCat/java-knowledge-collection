package io.ziheng.list.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {
    private class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        /**
         * Node -> newNode
         */
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}
/* EOF */