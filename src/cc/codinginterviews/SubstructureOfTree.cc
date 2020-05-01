/**
 * 二叉树节点
 */
class TreeNode {
public:
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int _val): val(_val), left(nullptr), right(nullptr) {
        // ...
    }
};

class SubstructureOfTree {
public:
    bool hasSubtree(TreeNode *rootA, TreeNode *rootB) {
        if (rootA == nullptr || rootB == nullptr) {
            return false;
        }
        if (isSameTree(rootA, rootB)) {
            return true;
        }
        return hasSubtree(rootA -> left, rootB)
            && hasSubtree(rootA -> right, rootB);
    }
private:
    bool isSameTree(TreeNode *rootA, TreeNode *rootB) {
        if (rootA == nullptr && rootB == nullptr) {
            return true;
        }
        if (rootA == nullptr || rootB == nullptr) {
            return false;
        }
        if (rootA -> val != rootB -> val) {
            return false;
        }
        return isSameTree(rootA -> left, rootB -> left)
            || isSameTree(rootA -> right, rootB -> right);
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */