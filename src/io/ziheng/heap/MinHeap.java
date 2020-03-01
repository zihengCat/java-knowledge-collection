package io.ziheng.heap;

import java.lang.reflect.Array;
import java.util.Random;

public class MinHeap<E extends Comparable<E>> implements HeapOperator<E> {

    public static void main(String[] args) {
        int capacity = 10;
        Random random = new Random();
        MinHeap<Integer> minHeap = new MinHeap<>(capacity);
        for (int i = 0; i < capacity; i++) {
            minHeap.insert(random.nextInt(capacity));
        }
        System.out.println(minHeap);
        while (!minHeap.isEmpty()) {
            System.out.printf("%d ", minHeap.extract());
        }
        System.out.println();
    }

    private static final int DEFAULT_CAPACITY = 8;
    private E[] elementData;
    private int capacity;
    private int size;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MinHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = initialCapacity + 1;
        this.elementData = (E[])Array.newInstance(Comparable.class, capacity);
        this.size = 0;
    }

    @Override
    public void insert(E e) {
        if (size + 1 > capacity) {
            throw new RuntimeException();
        }
        elementData[size + 1] = e;
        siftUp(size + 1);
        size++;
    }

    @Override
    public E extract() {
        E e = peek();
        swap(1, size);
        free(size);
        size--;
        siftDown(1);
        return e;
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return elementData[1];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void clear() {
        // ...
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < capacity; ++i) {
            stringBuilder.append(
                elementData[i] == null ? "null" : elementData[i].toString()
            );
            stringBuilder.append(", ");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private void siftUp(int index) {
        checkIndex(index);
        while (index > 1 && elementData[index].compareTo(
            elementData[getParentNode(index)]) < 0) {
            swap(index, getParentNode(index));
            index = getParentNode(index);
        }
    }

    private void siftDown(int index) {
        checkIndex(index);
        while (getLeftNode(index) < size + 1) {
            int leftIndex = getLeftNode(index);
            int rightIndex = getRightNode(index);
            int minIndex = leftIndex;
            if (rightIndex < size + 1 &&
                elementData[rightIndex].compareTo(elementData[leftIndex]) < 0) {
                minIndex = rightIndex;
            }
            if (elementData[index].compareTo(elementData[minIndex]) < 0) {
                break;
            } else {
                swap(index, minIndex);
                index = minIndex;
            }
        }
    }

    private int getParentNode(int index) {
        checkIndex(index);
        return index / 2;
    }

    private int getLeftNode(int index) {
        checkIndex(index);
        return index * 2;
    }

    private int getRightNode(int index) {
        checkIndex(index);
        return index * 2 + 1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException();
        }
    }

    private void swap(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        E e = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = e;
    }

    private void free(int index) {
        checkIndex(index);
        elementData[index] = null;
    }

}
/* EOF */