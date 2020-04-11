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
 * 剑指 Offer 面试题 25：合并两个排序的链表
 *
 * 题目描述：
 * 输入两个单调递增的链表，合并两个链表，合并后的链表也是单调递增的。
 *
 * 知识点：["链表"]
 */
public class MergeTwoSortedLinkedLists {

    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MergeTwoSortedLinkedLists obj = new MergeTwoSortedLinkedLists();
        ListNode listA = buildList(
            new int[]{1, 3, 5, 7, 9, }
        );
        ListNode listB = buildList(
            new int[]{2, 4, 6, 8, 10, }
        );
        System.out.println(
            printList(
                obj.mergeTwoSortedLinkedLists(listA, listB)
            )
        );
    }

    public ListNode mergeTwoSortedLinkedLists(ListNode listA, ListNode listB) {
        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;
        ListNode pA = listA;
        ListNode pB = listB;
        while (pA != null && pB != null) {
            if (pA.val < pB.val) {
                currentNode.next = pA;
                pA = pA.next;
            } else {
                currentNode.next = pB;
                pB = pB.next;
            }
            currentNode = currentNode.next;
        }
        while (pA != null) {
            currentNode.next = pA;
            pA = pA.next;
            currentNode = currentNode.next;
        }
        while (pB != null) {
            currentNode.next = pB;
            pB = pB.next;
            currentNode = currentNode.next;
        }
        return dummyHead.next;
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

}
/* EOF */