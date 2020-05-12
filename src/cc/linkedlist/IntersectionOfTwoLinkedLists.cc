#include <cstdio>
#include <set>

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
 * LeetCode 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
class Solution {
public:
    ListNode* getIntersectionNode(ListNode *headA, ListNode *headB) {
        std::set<ListNode*> aSet;
        while (headA != NULL) {
            aSet.insert(headA);
            headA = headA -> next;
        }
        while (headB != NULL) {
            if (aSet.find(headB) != aSet.end()) {
                return headB;
            }
            headB = headB -> next;
        }
        return NULL;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */