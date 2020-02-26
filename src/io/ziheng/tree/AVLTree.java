package io.ziheng.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<E extends Comparable<E>> {

    private class TreeNode<E> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;
        public int height;
        public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 1;
        }
    }

    private TreeNode<E> root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public static <E extends Comparable<E>> AVLTree<E> buildWith(E[] arr) {
        AVLTree<E> avlTree = new AVLTree<>();
        for (E element : arr) {
            avlTree.add(element);
        }
        return avlTree;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 获取节点高度。
     *
     * @param node
     * @return int
     */
    public int getHeight(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    /**
     * 获取节点平衡因子。
     *
     * @param node
     * @return int
     */
    public int getBalancedFactor(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean contains(E element) {
        //return containsRecursively(root, element);
        return containsIteratively(root, element);
    }
    private boolean containsIteratively(TreeNode<E> root, E element) {
        if (root == null) {
            return false;
        }
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            if (element.compareTo(currentNode.element) == 0) {
                return true;
            } else if (element.compareTo(currentNode.element) > 0) {
                currentNode = currentNode.right;
            } else if (element.compareTo(currentNode.element) < 0) {
                currentNode = currentNode.left;
            }
        }
        return false;
    }
    private boolean containsRecursively(TreeNode<E> root, E element) {
        if (root == null) {
            return false;
        }
        if (element.compareTo(root.element) > 0) {
            return containsRecursively(root.right, element);
        } else if (element.compareTo(root.element) < 0) {
            return containsRecursively(root.left, element);
        } else { // element.compareTo(root.element) == 0)
            return true;
        }
    }

    public void add(E element) {
        root = addRecursively(root, element);
        //root = addIteratively(root, element);
    }
    private TreeNode<E> addIteratively(TreeNode<E> root, E element) {
        if (root == null) {
            return new TreeNode<E>(element, null, null);
        }
        TreeNode<E> parentNode = root;
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            parentNode = currentNode;
            if (element.compareTo(currentNode.element) < 0) {
                currentNode = currentNode.left;
            } else if (element.compareTo(currentNode.element) > 0) {
                currentNode = currentNode.right;
            }
        }
        if (element.compareTo(parentNode.element) < 0) {
            parentNode.left = new TreeNode<E>(element, null, null);
        } else if (element.compareTo(parentNode.element) > 0) {
            parentNode.right = new TreeNode<E>(element, null, null);
        }
        return root;
    }
    private TreeNode<E> addRecursively(TreeNode<E> root, E element) {
        if (root == null) {
            size++;
            return new TreeNode<E>(element, null, null);
        }
        if (element.compareTo(root.element) < 0) {
            root.left = addRecursively(root.left, element);
        } else if (element.compareTo(root.element) > 0) {
            root.right = addRecursively(root.right, element);
        }
        // 更新树高
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        // 获取平衡因子
        int balancedFactor = getBalancedFactor(root);
        // 维持平衡
        if (Math.abs(balancedFactor) > 1) {
            //System.out.println("Unbalanced: " + balancedFactor);
            // LL
            if (balancedFactor > 1 && getBalancedFactor(root.left) >= 0) {
                return rightRotate(root);
            }
            // RR
            if (balancedFactor < -1 && getBalancedFactor(root.right) <= 0) {
                return leftRotate(root);
            }
            // LR
            if (balancedFactor > 1 && getBalancedFactor(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
            // RL
            if (balancedFactor < -1 && getBalancedFactor(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }
    /**
     * 左旋转。
     *
     * 平衡关系：t4 < y < t3 < x < t1 < z < t2
     *
     *   y                            x
     *  / \      leftRotate(y)      /   \
     * t4  x     ------------>     y     z
     *    / \                     / \   / \
     *   t3  z                   t4 t3 t1 t2
     *      / \
     *     t1 t2
     *
     * @param y
     * @return {@code TreeNode<E>}
     */
    private TreeNode<E> leftRotate(TreeNode<E> y) {
        TreeNode<E> x = y.right;
        TreeNode<E> t3 = x.left;
        x.left = y;
        y.right = t3;
        // 更新树高 Height (x -> y)
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
    /**
     * 右旋转。
     *
     * 平衡关系：t1 < z < t2 < x < t3 < y < t4
     *
     *       y                            x
     *      / \      rightRotate(y)     /   \
     *     x   t4    ------------->    z     y
     *    / \                         / \   / \
     *   z   t3                      t1 t2 t3 t4
     *  / \
     * t1 t2
     * 
     * @param y
     * @return {@code TreeNode<E>}
     */
    private TreeNode<E> rightRotate(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        TreeNode<E> t3 = x.right;
        x.right = y;
        y.left = t3;
        // 更新树高 Height (x -> y)
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    public void remove(E element) {
        root = removeRecursively(root, element);
    }
    public TreeNode<E> removeRecursively(TreeNode<E> root, E element) {
        if (root == null) {
            return null;
        }
        TreeNode<E> retNode = root;
        if (element.compareTo(root.element) > 0) {
            root.right = removeRecursively(root.right, element);
            retNode = root;
        } else if (element.compareTo(root.element) < 0) {
            root.left = removeRecursively(root.left, element);
            retNode = root;
        }
        if (element.compareTo(root.element) == 0) {
            // 叶子节点
            if (root.left == null && root.right == null) {
                freeNode(root);
                size--;
                retNode = null;
                //return null;
            }
            // 只有左子树
            else if (root.left != null && root.right == null) {
                TreeNode<E> leftNode = root.left;
                freeNode(root);
                size--;
                retNode = leftNode;
                //return leftNode;
            }
            // 只有右子树
            else if (root.left == null && root.right != null) {
                TreeNode<E> rightNode = root.right;
                freeNode(root);
                size--;
                retNode = rightNode;
                //return rightNode;
            }
            // 左右子树都存在 -> Hibbard Deletion（使用前驱/后继节点取代当前节点）
            // root.left != null && root.right != null
            else {
                /**
                 * 后继节点取代当前节点
                 */
                TreeNode<E> nextSuccessorNode =
                    findMinimumRecursively(root.right);
                swapNode(root, nextSuccessorNode);
                root.right = removeRecursively(root.right, nextSuccessorNode.element);
                retNode = root;
                //return root;
                /**
                 * 前驱节点取代当前节点
                 */
                //TreeNode<E> previousSuccessorNode =
                //    findMaximumRecursively(root.left);
                //previousSuccessorNode.left = removeMaxRecursively(root.left);
                //previousSuccessorNode.right = root.right;
                //freeNode(root);
                //return previousSuccessorNode;
            }
        }
        // 边界判断 -> 删除后返回节点为`NULL`
        if (retNode == null) {
            return retNode;
        }
        // 更新树高
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 计算平衡因子
        int balancedFactor = getBalancedFactor(retNode);
        // 维持平衡
        if (Math.abs(balancedFactor) > 1) {
            // LL
            if (balancedFactor > 1 && getBalancedFactor(retNode.left) >= 0) {
                return rightRotate(retNode);
            }
            // RR
            if (balancedFactor < -1 && getBalancedFactor(retNode.right) <= 0) {
                return leftRotate(retNode);
            }
            // LR
            if (balancedFactor > 1 && getBalancedFactor(retNode.left) < 0) {
                retNode.left = leftRotate(retNode.left);
                return rightRotate(retNode);
            }
            // RL
            if (balancedFactor < -1 && getBalancedFactor(retNode.right) > 0) {
                retNode.right = rightRotate(retNode.right);
                return leftRotate(retNode);
            }
        }
        return retNode;
    }

    public E removeMax() {
        E ret = findMaximum();
        root = removeMaxRecursively(root);
        return ret;
    }
    private TreeNode<E> removeMaxRecursively(TreeNode<E> root) {
        if (root.right == null) {
            TreeNode<E> leftNode = root.left;
            root.right = null;
            root.left = null;
            root.element = null;
            size--;
            return leftNode;
        }
        root.right = removeMaxRecursively(root.right);
        return root;
    }
    public E removeMin() {
        E ret = findMinimum();
        root = removeMinRecursively(root);
        return ret;
    }
    private TreeNode<E> removeMinRecursively(TreeNode<E> root) {
        if (root.left == null) {
            TreeNode<E> rightNode = root.right;
            freeNode(root);
            size--;
            return rightNode;
        }
        root.left = removeMinRecursively(root.left);
        return root;
    }
    public E findMinimum() {
        if (size == 0) {
            return null;
        }
        return findMinimumRecursively(root).element;
    }
    private TreeNode<E> findMinimumRecursively(TreeNode<E> root) {
        if (root.left == null) {
            return root;
        }
        return findMinimumRecursively(root.left);
    }
    public E findMaximum() {
        if (size == 0) {
            return null;
        }
        return findMaximumRecursively(root).element;
    }
    private TreeNode<E> findMaximumRecursively(TreeNode<E> root) {
        if (root.right == null) {
            return root;
        }
        return findMaximumRecursively(root.right);
    }

    public void travelsalTree() {

        System.out.printf("PreOrderIteratively: ");
        travelsalTreePreOrderIteratively(root);
        System.out.println();
        System.out.printf("PreOrder: ");
        travelsalTreePreOrder(root);
        System.out.println();
        System.out.printf("InOrder: ");
        travelsalTreeInOrder(root);
        System.out.println();
        System.out.printf("PostOrder: ");
        travelsalTreePostOrder(root);
        System.out.println();
        System.out.println("LevelOrder: ");
        travelsalTreeLevelOrder(root);
        System.out.println();
    }
    private void travelsalTreePostOrder(TreeNode<E> root) {
        // Left, Right, Root
        if (root == null) {
            return;
        }
        travelsalTreePostOrder(root.left);
        travelsalTreePostOrder(root.right);
        visitNode(root);
    }
    private void travelsalTreePreOrderIteratively(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            visitNode(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    private void travelsalTreePreOrder(TreeNode<E> root) {
        // Root, Left, Right
        if (root == null) {
            return;
        }
        visitNode(root);
        travelsalTreePreOrder(root.left);
        travelsalTreePreOrder(root.right);
    }
    private void travelsalTreeInOrder(TreeNode<E> root) {
        // Left, Root, Right
        if (root == null) {
            return;
        }
        travelsalTreeInOrder(root.left);
        visitNode(root);
        travelsalTreeInOrder(root.right);
    }
    private void travelsalTreeLevelOrder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        int level = 0;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<E> currentNode = queue.poll();
                visitNode(currentNode);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            System.out.println();
            level++;
        }
    }
    public boolean isBST() {
        return isBSTRecursively(root);
    }
    private boolean isBSTRecursively(TreeNode<E> root) {
        if (root == null) {
            return true;
        }
        boolean leftState = true;
        boolean rightState = true;
        if (root.left != null) {
            leftState = root.element.compareTo(root.left.element) > 0 ?
                        true : false;
        }
        if (root.right != null) {
            leftState = root.element.compareTo(root.right.element) < 0 ?
                        true : false;
        }
        return leftState && rightState;
    }
    private void visitNode(TreeNode<E> node) {
        System.out.print(node.element + " ");
    }
    private void freeNode(TreeNode<E> node) {
        node.left = null;
        node.right = null;
        node.element = null;
    }
    private void swapNode(TreeNode<E> nodeA, TreeNode<E> nodeB) {
        E element = nodeA.element;
        nodeA.element = nodeB.element;
        nodeB.element = element;
    }
}
/* EOF */