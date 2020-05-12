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
 * LeetCode 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
class Solution {
public:
    bool hasCycle(ListNode *head) {
        if (head == NULL) {
            return false;
        }
        // return hasCycleHashing(head);
        return hasCycleTwoPointer(head);
    }
private:
    bool hasCycleHashing(ListNode *head) {
        unordered_set<ListNode*> aSet;
        ListNode *currentNode = head;
        while (currentNode != NULL) {
            if (aSet.find(currentNode) != aSet.end()) {
                return true;
            }
            aSet.insert(currentNode);
            currentNode = currentNode -> next;
        }
        return false;
    }
    bool hasCycleTwoPointer(ListNode *head) {
        ListNode *pFast = head;
        ListNode *pSlow = head;
        while (pFast != NULL && pFast -> next != NULL) {
            pFast = pFast -> next -> next;
            pSlow = pSlow -> next;
            if (pFast == pSlow) {
                return true;
            }
        }
        return false;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */