#include <queue>

using namespace std;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
public:
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

/**
 * LeetCode 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
class Solution {
public:
    int widthOfBinaryTree(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }
        int maximumWidth = 0;
        queue<TreeNode*> aQueue;
        // BigInt problem...
        queue<long long> aQueueIndex;
        aQueue.push(root);
        aQueueIndex.push(1);
        while (!aQueue.empty()) {
            int size = aQueue.size();
            long long widthStart = 0;
            long long widthEnd = 0;
            for (int i = 0; i < size; i++) {
                TreeNode *currentNode = aQueue.front();
                long long currentIndex = aQueueIndex.front();
                aQueue.pop();
                aQueueIndex.pop();
                if (i == 0) {
                    widthStart = currentIndex;
                }
                if (i == size - 1) {
                    widthEnd = currentIndex;
                }
                if (currentNode -> left != nullptr) {
                    aQueue.push(currentNode -> left);
                    long long nextIndex = 2 * currentIndex;
                    aQueueIndex.push(nextIndex);
                }
                if (currentNode -> right != nullptr) {
                    aQueue.push(currentNode -> right);
                    long long nextIndex = 2 * currentIndex + 1;
                    aQueueIndex.push(nextIndex);
                }
            }
            int currentWidth = widthEnd - widthStart + 1;
            maximumWidth = currentWidth > maximumWidth ? currentWidth : maximumWidth;
        }
        return maximumWidth;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */