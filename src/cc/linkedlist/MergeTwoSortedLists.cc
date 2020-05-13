#include <cstdio>

/**
 * Definition for singly-linked list.
 */
class ListNode {
public:
    int val;
    ListNode *next;
    ListNode(): val(0), next(NULL) {
    }
    ListNode(int x): val(x), next(NULL) {
    }
    ListNode(int x, ListNode *next): val(x), next(next) {
    }
};

/**
 * LeetCode 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode *l1, ListNode *l2) {
        ListNode *dummyHead = new ListNode();
        ListNode *pA = l1;
        ListNode *pB = l2;
        ListNode *currentNode = dummyHead;
        while (pA != NULL && pB != NULL) {
            if (pA -> val < pB -> val) {
                currentNode -> next = pA;
                currentNode = currentNode -> next;
                pA = pA -> next;
            } else {
                currentNode -> next = pB;
                currentNode = currentNode -> next;
                pB = pB -> next;
            }
        }
        while (pA != NULL) {
            currentNode -> next = pA;
            currentNode = currentNode -> next;
            pA = pA -> next;
        }
        while (pB != NULL) {
            currentNode -> next = pB;
            currentNode = currentNode -> next;
            pB = pB -> next;
        }
        ListNode *retNode = dummyHead -> next;
        delete dummyHead;
        return retNode;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */