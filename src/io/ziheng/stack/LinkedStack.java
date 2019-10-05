package io.ziheng.stack;

import io.ziheng.stack.Stack;
import io.ziheng.stack.StackIsEmptyException;
import io.ziheng.stack.StackIsFullException;

public class LinkedStack<E> implements Stack<E> {

    private Node<E> base;
    private Node<E> top;
    private int size;

    public LinkedStack() {
        this.base = new Node<E>();
        this.top = this.base;
        this.size = 0;
    }
    public void push(E e) {
        Node<E> newNode = new Node<E>(e);
        newNode.setNext(null);
        newNode.setPrev(top);
        top.setNext(newNode);
        top = newNode;
        size++;
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        Node<E> currentNode = top;
        top = top.getPrev();
        top.setNext(null);
        E element = currentNode.getElement();
        currentNode.setPrev(null);
        currentNode.setNext(null);
        currentNode.setElement(null);
        size--;
        return element;
    }
    public E top() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        return top.getElement();
    }
    public void clear() {
        while (size > 0) {
            Node<E> currentNode = top;
            top = top.getPrev();
            top.setNext(null);
            currentNode.setElement(null);
            currentNode.setPrev(null);
            currentNode.setNext(null);
            size--;
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node() {
            this(null, null, null);
        }
        public Node(E element) {
            this(element, null, null);
        }
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
        public E getElement() {
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
/* EOF */
