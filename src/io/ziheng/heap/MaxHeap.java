package io.ziheng.heap;

import io.ziheng.heap.HeapOperation;

import java.lang.Comparable;
import java.lang.reflect.Array;

public class MaxHeap<E extends Comparable<E>> implements HeapOperation<E> {

    private static final int DEFAULT_CAPACITY = 8;
    private E[] elementData;
    private int capacity;
    private int size;

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(
                "[ERROR]: Initial capacity must be greater than zero."
            );
        }
        this.elementData = (E[])Array.newInstance(
            Comparable.class,
            initialCapacity
        );
        this.capacity = initialCapacity;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int initialCapacity = arr.length;
        this.elementData = (E[])Array.newInstance(
            Comparable.class,
            initialCapacity
        );
        this.capacity = initialCapacity;
        this.size = initialCapacity;
        for (int i = 0; i < initialCapacity; ++i) {
            elementData[i] = arr[i];
        }
        heapifyInternal();
    }

    public static <E extends Comparable<E>> MaxHeap<E> heapify(E[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int initialCapacity = arr.length;
        MaxHeap<E> maxHeap = new MaxHeap<E>(initialCapacity);
        for (int i = 0; i < arr.length; ++i) {
            maxHeap.add(arr[i]);
        }
        return maxHeap;
    }

    @Override
    public void add(E element) {
        elementData[size] = element;
        size++;
        siftUpNode(size - 1);
    }

    @Override
    public E extract() {
        E element = peek();
        swapNode(0, size - 1);
        freeNode(size - 1);
        size--;
        siftDownNode(0);
        return element;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return elementData[0];
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
        for (int i = 0; i < capacity; ++i) {
            freeNode(i);
        }
        size = 0;
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

    private void heapifyInternal() {
        for (int i = getParentNode(size - 1); i >= 0; i--) {
            siftDownNode(i);
        }
    }

    private void siftUpNode(int index) {
        checkIndex(index);
        while (index > 0 && elementData[getParentNode(index)]
            .compareTo(elementData[index]) < 0) {
            swapNode(index, getParentNode(index));
            index = getParentNode(index);
        }
    }

    private void siftDownNode(int index) {
        checkIndex(index);
        while (getLeftNode(index) < size) {
            int leftNodeIndex = getLeftNode(index);
            int rightNodeIndex = getRightNode(index);
            int maxNodeIndex = leftNodeIndex;
            if (rightNodeIndex < size &&
                elementData[rightNodeIndex].compareTo(
                elementData[leftNodeIndex]) > 0) {
                maxNodeIndex = rightNodeIndex;
            }
            if (elementData[index].compareTo(
                elementData[maxNodeIndex]) >= 0) {
                break;
            } else {
                swapNode(index, maxNodeIndex);
                index = maxNodeIndex;
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.capacity) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int getParentNode(int index) {
        checkIndex(index);
        return (index - 1) / 2;
    }

    private int getLeftNode(int index) {
        checkIndex(index);
        return index * 2 + 1;
    }

    private int getRightNode(int index) {
        checkIndex(index);
        return index * 2 + 2;
    }

    private void swapNode(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        E element = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = element;
    }

    private void freeNode(int index) {
        checkIndex(index);
        elementData[index] = null;
    }

}
/* EOF */