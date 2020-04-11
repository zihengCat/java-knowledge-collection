package io.ziheng.codinginterviews;

import java.util.HashSet;
import java.util.Set;

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
 * 剑指 Offer 面试题 23：链表中环的入口节点
 *
 * 题目描述：
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出 null 。
 *
 * 知识点：["链表"]
 */
public class EntryNodeOfRingInLinkedList {

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
     * 剑指 Offer 面试题 23：链表中环的入口节点
     *
     * @param head
     * @return ListNode
     */
    public ListNode entryNodeOfRing(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 哈希表法
        // return entryNodeOfRingHashing(head);
        // 双指针法
        return entryNodeOfRingTwoPointer(head);
    }

    /**
     * 双指针法
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return ListNode
     */
    private ListNode entryNodeOfRingTwoPointer(ListNode head) {
        boolean hasCycle = false;
        ListNode pSlow = head;
        ListNode pFast = head;
        while (pFast != null && pFast.next != null) {
            pSlow = pSlow.next;
            pFast = pFast.next.next;
            if (pSlow == pFast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        ListNode pA = head;
        ListNode pB = pFast;
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }

    /**
     * 哈希表法
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return ListNode
     */
    private ListNode entryNodeOfRingHashing(ListNode head) {
        Set<ListNode> aSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (aSet.contains(currentNode)) {
                return currentNode;
            }
            aSet.add(currentNode);
            currentNode = currentNode.next;
        }
        return null;
    }
}
/* EOF */