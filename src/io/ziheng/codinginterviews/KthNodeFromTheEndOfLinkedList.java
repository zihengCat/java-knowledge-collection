package io.ziheng.codinginterviews;

import java.util.LinkedList;
import java.util.List;

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
 * 剑指 Offer 面试题 22：链表倒数第 k 个节点
 *
 * 题目描述：
 * 输入一个链表，输出该链表中倒数第 k 个节点。
 *
 * 知识点：["链表"]
 */
public class KthNodeFromTheEndOfLinkedList {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        KthNodeFromTheEndOfLinkedList obj = new KthNodeFromTheEndOfLinkedList();
        int[] arr = new int[]{1, 2, 3, 4, 5, };
        ListNode head = buildList(arr);
        System.out.println(
            printList(head)
        );
        ListNode node = obj.findKthNodeFromTheEnd(head, 5);
        System.out.println(
            node == null ? node : node.val
        );
    }

    /**
     * 单向链表转列表
     *
     * @param head
     * @return ListNode
     */
    public static List<Integer> printList(ListNode head) {
        List<Integer> resultList = new LinkedList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            resultList.add(currentNode.val);
            currentNode = currentNode.next;
        }
        return resultList;
    }

    /**
     * 快速构建单向链表
     *
     * @param arr
     * @return ListNode
     */
    public static ListNode buildList(int[] arr) {
        ListNode dummyhead = new ListNode(0);
        ListNode currentNode = dummyhead;
        for (int n : arr) {
            ListNode node = new ListNode(n);
            currentNode.next = node;
            currentNode = currentNode.next;
        }
        return dummyhead.next;
    }

    /**
     * 剑指 Offer 面试题 22：链表倒数第 k 个节点
     *
     * @param head
     * @param k
     * @return ListNode
     */
    public ListNode findKthNodeFromTheEnd(ListNode head, int k) {
        // 处理异常情况
        if (head == null || k < 0) {
            return null;
        }
        ListNode pA = head;
        ListNode pB = head;
        for (int i = 0; i < k; i++) {
            // k 越界
            if (pA == null) {
                return null;
            }
            pA = pA.next;
        }
        while (pA != null) {
            pA = pA.next;
            pB = pB.next;
        }
        return pB;
    }
}
/* EOF */