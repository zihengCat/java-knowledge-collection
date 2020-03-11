#include <stdio.h>
#include "linked_list.h"

class LinkedListCycle {
public:
    bool hasCycle(ListNode *head);
};

bool LinkedListCycle::hasCycle(ListNode *head) {
    ListNode *pSlow = head;
    ListNode *pFast = head;
    while (pFast != NULL && pFast -> next != NULL) {
        pSlow = pSlow -> next;
        pFast = pFast -> next -> next;
        if (pSlow == pFast) {
            return true;
        }
    }
    return false;
}

int main(int argc, char const *argv[]) {
    return 0;
}
/* EOF */