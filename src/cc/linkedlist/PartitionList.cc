#include <cstdlib>

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
 * LeetCode 86. Partition List
 * https://leetcode.com/problems/partition-list/
 */
class Solution {
public:
    ListNode* partition(ListNode *head, int x) {
        if (head == NULL) {
            return head;
        }
        ListNode *pLess = new ListNode();
        ListNode *pGreater = new ListNode();
        ListNode *pLessHead = pLess;
        ListNode *pGreaterHead = pGreater;
        ListNode *currentNode = head;
        while (currentNode != NULL) {
            ListNode *nextNode = currentNode -> next;
            if (currentNode -> val < x) {
                currentNode -> next = NULL;
                pLess -> next = currentNode;
                pLess = currentNode;
            } else {
                currentNode -> next = NULL;
                pGreater -> next = currentNode;
                pGreater = currentNode;
            }
            currentNode = nextNode;
        }
        pLess -> next = pGreaterHead -> next;
        ListNode *retNode = pLessHead -> next;
        delete pLessHead;
        delete pGreaterHead;
        return retNode;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */