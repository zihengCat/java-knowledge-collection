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
 * 剑指 Offer 面试题 52：两个链表的第一个公共节点
 *
 * 题目描述：
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 知识点：["链表"]
 */
public class IntersectionNodeOfTwoLinkedLists {

    /**
     * 剑指 Offer 面试题 52：两个链表的第一个公共节点
     *
     * @param pHeadA
     * @param pHeadB
     * @return ListNode
     */
    public ListNode findFirstIntersectionNode(ListNode pHeadA, ListNode pHeadB) {
        if (pHeadA == null || pHeadB == null) {
            return null;
        }
        // 暴力法
        // return findFirstIntersectionNodeBruteForce(pHeadA, pHeadB);

        // 哈希表法
        // return findFirstIntersectionNodeHashing(pHeadA, pHeadB);

        // 双指针法
        return findFirstIntersectionNodeTwoPointer(pHeadA, pHeadB);
    }

    /**
     * 双指针法
     * 让长链表先走过两链表长度差的步长，再进行节点重合判断。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param pHeadA
     * @param pHeadB
     * @return ListNode
     */
    private ListNode findFirstIntersectionNodeTwoPointer(
        ListNode pHeadA, ListNode pHeadB) {
        int lenA = findLength(pHeadA);
        int lenB = findLength(pHeadB);
        ListNode pA = pHeadA;
        ListNode pB = pHeadB;
        if (lenA > lenB) {
            int diff = lenA - lenB;
            for (int i = 0; i < diff; i++) {
                pA = pA.next;
            }
        } else {
            int diff = lenB - lenA;
            for (int i = 0; i < diff; i++) {
                pB = pB.next;
            }
        }
        while (pA != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }

    private int findLength(ListNode head) {
        int cnt = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            cnt++;
            currentNode = currentNode.next;
        }
        return cnt;
    }

    /**
     * 哈希表法
     *
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(n)
     *
     * @param pHeadA
     * @param pHeadB
     * @return ListNode
     */
    private ListNode findFirstIntersectionNodeHashing(
        ListNode pHeadA, ListNode pHeadB) {
        Set<ListNode> aSet = new HashSet<>();
        ListNode currentNode = pHeadA;
        while (currentNode != null) {
            aSet.add(currentNode);
            currentNode = currentNode.next;
        }
        currentNode = pHeadB;
        while (currentNode != null) {
            if (aSet.contains(currentNode)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    /**
     * 暴力法
     *
     * 时间复杂度：O(n * m)
     * 空间复杂度：O(1)
     *
     * @param pHeadA
     * @param pHeadB
     * @return ListNode
     */
    private ListNode findFirstIntersectionNodeBruteForce(
        ListNode pHeadA, ListNode pHeadB) {
        ListNode pA = pHeadA;
        while (pA != null) {
            if (hasNode(pA, pHeadB)) {
                return pA;
            }
            pA = pA.next;
        }
        return null;
    }

    private boolean hasNode(ListNode node, ListNode head) {
        ListNode currentNode = head;
        while (currentNode != null) {
            if (node == currentNode) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

}
/* EOF */