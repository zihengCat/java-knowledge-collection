#ifndef __LINKED_LIST__H__
#define __LINKED_LIST__H__
#include <cstdio>
/**
 * Definition for singly-linked list.
 */
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int val, ListNode *next): val(val), next(next) {
        // ...
    }
    ListNode(int val): val(val), next(NULL) {
        // ...
    }
};
#endif
/* EOF */