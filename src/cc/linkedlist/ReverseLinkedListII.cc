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
 * LeetCode 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
class Solution {
public:
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        ListNode *dummyHead = new ListNode(0);
        dummyHead -> next = head;
        ListNode *previousNode = dummyHead;
        for (int i = 0; i < m - 1; i++) {
            previousNode = previousNode -> next;
        }
        int len = n - m + 1;
        ListNode *newHead = NULL;
        ListNode *newTail = previousNode -> next;
        ListNode *currentNode = previousNode -> next;
        while (currentNode != NULL && len > 0) {
            ListNode *next = currentNode -> next;
            currentNode -> next = newHead;
            newHead = currentNode;
            currentNode = next;
            len--;
        }
        newTail -> next = currentNode;
        previousNode -> next = newHead;
        return dummyHead -> next;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */