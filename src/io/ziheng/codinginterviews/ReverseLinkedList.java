package io.ziheng.codinginterviews;

import java.util.List;
import java.util.LinkedList;

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
 * 剑指 Offer 面试题 24：反转链表
 *
 * 题目描述：
 * 输入一个链表，反转链表后输出新链表的头节点。
 *
 * 知识点：["链表"]
 */
public class ReverseLinkedList {

    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        ReverseLinkedList obj = new ReverseLinkedList();
        ListNode head = buildList(
            new int[]{1, 2, 3, 4, 5, }
        );
        System.out.println(
            "Original Linked List: "
            + printList(head)
        );
        System.out.println(
            "Reversed Linked List: "
            + printList(
                // 迭代
                obj.reverseLinkedListIteratively(head)
                // 递归
                // obj.reverseLinkedListRecursively(head, null)
            )
        );
    }

    /**
     * 迭代
     *
     * @param head
     * @return ListNode
     */
    public ListNode reverseLinkedListIteratively(ListNode head) {
        // 处理异常情况
        if (head == null) {
            return null;
        }
        ListNode previousNode = null;
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }

    /**
     * 递归 {@code reverseLinkedListRecursively(head, null)}
     *
     * @param head
     * @param node
     * @return ListNode
     */
    public ListNode reverseLinkedListRecursively(ListNode head, ListNode node) {
        if (head == null) {
            return node;
        }
        ListNode next = head.next;
        head.next = node;
        return reverseLinkedListRecursively(next, head);
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

}
/* EOF */