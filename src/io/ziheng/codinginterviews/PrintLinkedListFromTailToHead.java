package io.ziheng.codinginterviews;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 单链表
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
 * 剑指 Offer 面试题 05：从尾到头打印链表
 *
 * 题目描述：
 * 输入一个单向链表，按链表从尾到头的顺序返回一个 ArrayList 。
 *
 * 知识点：["链表"]
 */
public class PrintLinkedListFromTailToHead {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        PrintLinkedListFromTailToHead obj = new PrintLinkedListFromTailToHead();
        int[] arr = new int[]{1, 2, 3, 4, 5, };
        ListNode head = buildList(arr);
        System.out.println(
            "Original LinkedList: "
            + Arrays.toString(arr)
        );
        System.out.println(
            "Reversed LinkedList: "
            + obj.printListFromTailToHead(head).toString()
        );
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
     * 剑指 Offer 面试题 05：从尾到头打印链表
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return {@code List<Integer>}
     */
    public List<Integer> printListFromTailToHead(ListNode head) {
        List<Integer> resultList = new ArrayList<>();
        Deque<Integer> aStack = new LinkedList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            aStack.push(currentNode.val);
            currentNode = currentNode.next;
        }
        while (!aStack.isEmpty()) {
            resultList.add(aStack.pop());
        }
        return resultList;
    }
}
/* EOF */