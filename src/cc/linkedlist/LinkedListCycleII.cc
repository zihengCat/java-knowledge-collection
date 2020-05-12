#include <cstdlib>
#include <unordered_set>

using namespace std;

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
 * LeetCode 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */
class Solution {
public:
    ListNode* detectCycle(ListNode *head) {
        if (head == NULL) {
            return head;
        }
        // return detectCycleHashing(head);
        return detectCycleTwoPointer(head);
    }
private:
    ListNode* detectCycleTwoPointer(ListNode *head) {
        bool hasCycle = false;
        ListNode *pFast = head;
        ListNode *pSlow = head;
        while (pFast != NULL && pFast -> next != NULL) {
            pFast = pFast -> next -> next;
            pSlow = pSlow -> next;
            if (pFast == pSlow) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return NULL;
        }
        ListNode *pA = head;
        ListNode *pB = pFast;
        while (pA != pB) {
            pA = pA -> next;
            pB = pB -> next;
        }
        return pA;
    }
    ListNode* detectCycleHashing(ListNode *head) {
        ListNode *currentNode = head;
        unordered_set<ListNode*> aSet;
        while (currentNode != NULL) {
            if (aSet.find(currentNode) != aSet.end()) {
                return currentNode;
            }
            aSet.insert(currentNode);
            currentNode = currentNode -> next;
        }
        return NULL;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */