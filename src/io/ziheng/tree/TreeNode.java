package io.ziheng.tree;
public class TreeNode<E> {
    private E element;
    private TreeNode<E> leftNode;
    private TreeNode<E> rightNode;
    public TreeNode() {
        this(null, null, null);
    }
    public TreeNode(E element) {
        this(element, null, null);
    }
    public TreeNode(E element, TreeNode<E> leftNode, TreeNode<E> rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    public E getElement() {
        return element;
    }
    public void setElement(E element) {
        this.element = element;
    }
    public TreeNode<E> getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(TreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }
    public TreeNode<E> getRightNode() {
        return rightNode;
    }
    public void setRightNode(TreeNode<E> rightNode) {
        this.rightNode = rightNode;
    }
    @Override
    public String toString() {
        return String.format(
            "TreeNode{%s}", element == null ? "null" : element.toString()
        );
    }
}
/* EOF */
