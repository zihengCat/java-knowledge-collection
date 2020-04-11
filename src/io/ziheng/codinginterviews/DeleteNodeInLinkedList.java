package io.ziheng.codinginterviews;

/**
 * 单链表节点
 */
class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 剑指 Offer 面试题 18：删除链表节点
 *
 * 题目描述：
 * 给定单向链表头节点指针与目标节点指针，
 * 请以 O(1) 时间复杂度从单向链表中删除该目标节点。
 *
 * 知识点：["链表"]
 */
public class DeleteNodeInLinkedList {

    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // ...
    }

    /**
     * 剑指 Offer 面试题 18：删除链表节点
     *
     * @param head
     * @param node
     * @return void
     */
    public void deleteNode(ListNode head, ListNode node) {
        // 异常情况
        if (head == null || node == null) {
            return;
        }
        // 尾节点
        if (node.next == null) {
            ListNode currentNode = head;
            while (currentNode.next != node) {
                currentNode = currentNode.next;
            }
            currentNode.next = null;
            freeNode(node);
        // 非尾节点
        } else {
            ListNode delNode = node.next;
            node.val = delNode.val;
            node.next = delNode.next;
            freeNode(delNode);
        }
    }

    private void freeNode(ListNode node) {
        node.val = 0;
        node.next = null;
    }
}
/* EOF */