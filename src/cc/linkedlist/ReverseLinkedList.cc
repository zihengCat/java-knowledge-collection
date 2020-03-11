#include <stdio.h>
#include "linked_list.h"

class ReverseLinkedList {
public:
    ListNode* reverseList(ListNode* head) {
        return reverseListIteratively(head);
    }
private:
    ListNode* reverseListIteratively(ListNode* headNode);
    ListNode* reverseListRecursively(ListNode* headNode, ListNode* prevNode);
};


/**
 * 反转单链表 -> 递归
 *
 * @param headNode
 * @param prevNode
 * @return ListNode*
 */
ListNode* ReverseLinkedList::reverseListRecursively(
    ListNode* headNode, ListNode* prevNode) {
    if (headNode == NULL) {
        return prevNode;
    }
    ListNode* nextNode = headNode -> next;
    headNode -> next = prevNode;
    return reverseListRecursively(nextNode, headNode);
}

/**
 * 反转单链表 -> 迭代
 *
 * @param headNode
 * @return ListNode*
 */
ListNode* ReverseLinkedList::reverseListIteratively(ListNode* headNode) {
    ListNode* previousNode = NULL;
    ListNode* currentNode = headNode;
    ListNode* nextNode;
    while (currentNode != NULL) {
        nextNode = currentNode -> next;
        currentNode -> next = previousNode;
        previousNode = currentNode;
        currentNode = nextNode;
    }
    return previousNode;
}


int main(int argc, char const *argv[]) {
    ListNode listNode = ListNode(1, NULL);
    printf("%d\n", listNode.val);
    return 0;
}
/* EOF */