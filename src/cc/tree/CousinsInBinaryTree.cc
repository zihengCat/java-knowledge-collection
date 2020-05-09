#include <iostream>

/**
 * Definition for a binary tree node.
 */
class TreeNode {
public:
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(): val(0), left(nullptr), right(nullptr) {
        // ...
    }
    TreeNode(int x): val(x), left(nullptr), right(nullptr) {
        // ...
    }
    TreeNode(int x, TreeNode *left, TreeNode *right):val(x), left(left), right(right) {
        // ...
    }
};

/**
 * LeetCode 993. Cousins in Binary Tree
 * https://leetcode.com/problems/cousins-in-binary-tree/
 */
class Solution {
public:
    bool isCousins(TreeNode *root, int x, int y) {
        initializeDataMembers();
        getDepthAndParent(root, nullptr, 0, x, &xDepth, &xParent);
        getDepthAndParent(root, nullptr, 0, y, &yDepth, &yParent);
        return xDepth == yDepth && xParent != yParent;
    }
private:
    int xDepth;
    int yDepth;
    TreeNode *xParent;
    TreeNode *yParent;
    void initializeDataMembers() {
        this -> xDepth = -1;
        this -> yDepth = -1;
        this -> xParent = nullptr;
        this -> yParent = nullptr;
    }
    void getDepthAndParent(
        TreeNode *rootNode,
        TreeNode *parentNode,
        int currentDepth,
        int val,
        int *pDepth,
        TreeNode **pParent) {
        if (rootNode == nullptr) {
            return;
        }
        if (val == rootNode -> val) {
            *pDepth = currentDepth;
            *pParent = parentNode;
        } else {
            getDepthAndParent(
                rootNode -> left,
                rootNode,
                1 + currentDepth,
                val,
                pDepth,
                pParent
            );
            getDepthAndParent(
                rootNode -> right,
                rootNode,
                1 + currentDepth,
                val,
                pDepth,
                pParent
            );
        }
    }
};

int main(int argc, char const *argv[]) {
    TreeNode node5 = TreeNode(5);
    TreeNode node4 = TreeNode(4);
    TreeNode node3 = TreeNode(3, nullptr, &node5);
    TreeNode node2 = TreeNode(2, nullptr, &node4);
    TreeNode node1 = TreeNode(1, &node2, &node3);
    TreeNode *root = &node1;
    Solution obj = Solution();
    std::cout << obj.isCousins(root, 5, 4) << std::endl;
    std::cout << obj.isCousins(root, 2, 3) << std::endl;
    return 0;
}
/* EOF */