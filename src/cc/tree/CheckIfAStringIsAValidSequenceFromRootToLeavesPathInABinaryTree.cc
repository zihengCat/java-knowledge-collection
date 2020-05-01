#include <vector>

using namespace std;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
public:
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(): val(0), left(nullptr), right(nullptr) {
    }
    TreeNode(int x): val(x), left(nullptr), right(nullptr) {
    }
    TreeNode(int x, TreeNode *left, TreeNode *right): val(x), left(left), right(right) {
    }
};
/**
 * LeetCode ???. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
 * ...
 */
class Solution {
public:
    bool isValidSequence(TreeNode *root, vector<int>& arr) {
        return isValidSequence(root, arr, 0);
    }
private:
    bool isValidSequence(TreeNode *root, vector<int>& arr, int currentIndex) {
        if (root == nullptr || currentIndex == arr.size()) {
            return false;
        }
        if (root -> val != arr[currentIndex]) {
            return false;
        }
        if (currentIndex == arr.size() - 1
            && root -> left == nullptr
            && root -> right == nullptr) {
            return true;
        }
        return isValidSequence(root -> left, arr, currentIndex + 1)
            || isValidSequence(root -> right, arr, currentIndex + 1);
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */