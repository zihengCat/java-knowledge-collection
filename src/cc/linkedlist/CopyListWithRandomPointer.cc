#include <cstdlib>
#include <unordered_map>

using namespace std;

/**
 * Definition for a Node.
 */
class Node {
public:
    int val;
    Node *next;
    Node *random;
    Node(int _val): val(_val), next(NULL), random(NULL) {
    }
};

/**
 * LeetCode 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
class Solution {
public:
    Node* copyRandomList(Node *head) {
        if (head == NULL) {
            return head;
        }
        unordered_map<Node*, Node*> aMap;
        Node *currentNode = head;
        while (currentNode != NULL) {
            aMap.insert(make_pair(currentNode, new Node(currentNode -> val)));
            currentNode = currentNode -> next;
        }
        currentNode = head;
        while (currentNode != NULL) {
            aMap[currentNode] -> next = aMap[currentNode -> next];
            aMap[currentNode] -> random = aMap[currentNode -> random];
            currentNode = currentNode -> next;
        }
        return aMap[head];
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */