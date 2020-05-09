#include <stdio.h>

/**
 * Definition for singly-linked list.
 */
class ListNode {
public:
    int val;
    ListNode *next;
    ListNode(): val(0), next(NULL) {
        // ...
    }
    ListNode(int x): val(x), next(NULL) {
        // ...
    }
    ListNode(int x, ListNode *next): val(x), next(next) {
        // ...
    }
};

/**
 * LeetCode 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 */
class Solution {
public:
    ListNode* reverseList(ListNode *head) {
        // return reverseListIteratively(head);
        return reverseListRecursively(head, NULL);
    }
private:
    /**
     * 反转单链表 -> 迭代
     *
     * @param headNode
     * @return ListNode*
     */
    ListNode* reverseListIteratively(ListNode *head) {
        ListNode *newHead = NULL;
        while (head) {
            ListNode *next = head -> next;
            head -> next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    /**
     * 反转单链表 -> 递归
     *
     * @param headNode
     * @param prevNode
     * @return ListNode*
     */
    ListNode* reverseListRecursively(ListNode *headNode, ListNode *prevNode) {
        if (headNode == NULL) {
            return prevNode;
        }
        ListNode* nextNode = headNode -> next;
        headNode -> next = prevNode;
        return reverseListRecursively(nextNode, headNode);
    }
};

int main(int argc, char const *argv[]) {
    ListNode listNode = ListNode(1, NULL);
    printf("%d\n", listNode.val);
    return 0;
}
/* EOF */