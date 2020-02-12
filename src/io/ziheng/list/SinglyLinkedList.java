package io.ziheng.list;

import io.ziheng.list.List;

public class SinglyLinkedList<E> implements List<E> {

    private class Node<EL> {
        public EL data;
        public Node<EL> next;
        public Node() {
            this(null, null);
        }
        public Node(EL data, Node<EL> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    //private Node<E> tail;
    private int size;
    private Node<E> dummyNode;

    public SinglyLinkedList() {
        this.dummyNode = new Node<E>();
        this.head = dummyNode;
        //this.tail = dummyNode;
        this.size = 0;
    }

    public static void main(String[] args) {
        List<Integer> list = new SinglyLinkedList<Integer>();
        System.out.println(list);
        for (int i = 0; i < 10; ++i) {
            list.addLast(i);
        }
        System.out.println(list);
        list.add(10, 100);
        System.out.println(list);
        list.add(0, 999);
        list.remove(2);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.indexOf(5));
        System.out.println(list.contains(128));
        System.out.println(list.get(0));
        list.set(0, 128);
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        for (Node<E> node = head.next; node != null; node = node.next) {
            if ((element == null && element == node.data) ||
                (node.data.equals(element))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        Node<E> node = head.next;
        while (node != null) {
            Node<E> currentNode = node;
            node = node.next;
            freeNode(currentNode);
        }
        freeNode(dummyNode);
        size = 0;
    }

    @Override
    public int indexOf(E element) {
        int index = -1;
        for (Node<E> node = head.next; node != null; node = node.next) {
            index++;
            if ((element == null && element == node.data) ||
                (node.data.equals(element))) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return indexNode(index).data;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = indexNode(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (index == size) {
            addLast(element);
        } else {
            Node<E> newNode = new Node<E>(element, null);
            Node<E> currentNode = head.next;
            Node<E> previousNode = head;
            for (int i = index; i > 0; --i) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            newNode.next = currentNode;
            previousNode.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<E>(element, null);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<E>(element, null);
        Node<E> tailNode = head;
        while (tailNode.next != null) {
            tailNode = tailNode.next;
        }
        newNode.next = null;
        tailNode.next = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> currentNode = head.next;
        Node<E> previousNode = head; 
        for (int i = index; i > 0; i--) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        E element = currentNode.data;
        previousNode.next = currentNode.next;
        freeNode(currentNode);
        size--;
        return element;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("[ERROR]: LinkedList is empty!");
        }
        Node<E> firstNode = head.next;
        E element = firstNode.data;
        head.next = head.next.next;
        freeNode(firstNode);
        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("[ERROR]: LinkedList is empty!");
        }
        Node<E> tailNode = head.next;
        Node<E> prevNode = head;
        while (tailNode.next != null) {
            prevNode = tailNode;
            tailNode = tailNode.next;
        }
        prevNode.next = null;
        E element = tailNode.data;
        freeNode(tailNode);
        return element;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (Node<E> node = head.next; node != null; node = node.next) {
            stringBuilder.append(
                node.data == null ? "null" : node.data.toString()
            );
            stringBuilder.append(", ");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private Node<E> indexNode(int index) {
        checkIndex(index);
        Node<E> node = head.next;
        for (int i = index; i > 0; --i) {
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void freeNode(Node<E> node) {
        if (node == null) {
            return;
        }
        node.data = null;
        node.next = null;
    }
}
/* EOF */