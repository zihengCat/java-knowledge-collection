#include <cstdio>
#include <vector>
#include <algorithm>

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
 * LeetCode 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        // return mergeKListsOverwrite(lists);
        // return mergeKListsTwoMerge(lists);
        return mergeKListsDivideAndConquer(lists);
    }
private:
    ListNode* mergeKListsDivideAndConquer(vector<ListNode*>& lists) {
        if (lists.size() == 0) {
            return NULL;
        }
        return mergeKListsDivideAndConquer0(
            lists, 0, lists.size() - 1
        );
    }
    ListNode* mergeKListsDivideAndConquer0(
        vector<ListNode*>& lists,
        int left,
        int right) {
        if (left >= right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode *leftList = mergeKListsDivideAndConquer0(
            lists, left, mid
        );
        ListNode *rightList = mergeKListsDivideAndConquer0(
            lists, mid + 1, right
        );
        return mergeTwoLists(leftList, rightList);
    }
    ListNode* mergeKListsTwoMerge(vector<ListNode*>& lists) {
        if (lists.size() == 0) {
            return NULL;
        }
        ListNode *pList = lists[0];
        for (int i = 1; i < lists.size(); i++) {
            pList = mergeTwoLists(pList, lists[i]);
        }
        return pList;
    }
    ListNode* mergeTwoLists(ListNode *l1, ListNode *l2) {
        ListNode *pA = l1;
        ListNode *pB = l2;
        ListNode *dummyHead = new ListNode();
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
        // avoid memory leak
        ListNode *retNode = dummyHead -> next;
        delete dummyHead;
        return retNode;
    }
    ListNode* mergeKListsOverwrite(vector<ListNode*>& lists) {
        vector<int> aVector;
        for (int i = 0; i < lists.size(); i++) {
            ListNode *currentNode = lists[i];
            while (currentNode != NULL) {
                aVector.push_back(currentNode -> val);
                currentNode = currentNode -> next;
            }
        }
        sort(aVector.begin(), aVector.end(), less<int>());
        ListNode *dummyHead = new ListNode();
        ListNode *currentNode = dummyHead;
        for (vector<int>::iterator iter = aVector.begin();
            iter != aVector.end();
            iter++) {
            currentNode -> next = new ListNode(*iter);
            currentNode = currentNode -> next;
        }
        // avoid memory leak
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