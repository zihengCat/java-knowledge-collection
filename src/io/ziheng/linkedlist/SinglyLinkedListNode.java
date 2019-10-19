package io.ziheng.linkedlist;
public class SinglyLinkedListNode<E> {
    private E element;
    private SinglyLinkedListNode<E> next;
    public SinglyLinkedListNode() {
        this(null, null);
    }
    public SinglyLinkedListNode(E element) {
        this(element, null);
    }
    public SinglyLinkedListNode(E element, SinglyLinkedListNode<E> next) {
        this.element = element;
        this.next = next;
    }
    public E getElement() {
        return element;
    }
    public void setElement(E element) {
        this.element = element;
    }
    public SinglyLinkedListNode<E> getNext() {
        return next;
    }
    public void setNext(SinglyLinkedListNode<E> next) {
        this.next = next;
    }
}
/* EOF */
