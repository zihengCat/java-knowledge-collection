package io.ziheng.array;

import io.ziheng.list.List;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 8;
    private E[] elementData;
    private int capacity;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = DEFAULT_CAPACITY;
        }
        this.elementData = (E[])new Object[initialCapacity];
        this.capacity = initialCapacity;
        this.size = 0;
    }

    @Override
    public void addLast(E element) {
        if (size + 1 > capacity) {
            grow();
        }
        elementData[size] = element;
        size++;
    }

    @Override
    public void addFirst(E element) {
        if (size + 1 > capacity) {
            grow();
        }
        for (int i = size; i > 0; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[0] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size + 1 > capacity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E element = elementData[size - 1];
        freeElement(size - 1);
        size--;
        if (size * 2 <= capacity) {
            reduce();
        }
        return element;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = elementData[0];
        for (int i = 0; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        if (size * 2 <= capacity) {
            reduce();
        }
        return element;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E element = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        if (size * 2 <= capacity) {
            reduce();
        }
        return element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            freeElement(i);
            size--;
        }
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
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if(elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (!List.class.isInstance(obj)) {
            return false;
        }
        List<E> aList = (List<E>)obj;
        if (size != aList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elementData[i].equals(aList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < size; i++) {
            stringBuilder.append(
                elementData[i] == null ? "null" : elementData[i].toString()
            );
            stringBuilder.append(", ");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public int getCapacity() {
        assert capacity == elementData.length;
        return capacity;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void freeElement(int index) {
        checkIndex(index);
        elementData[index] = null;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCapacity = this.capacity;
        E[] oldElementData = this.elementData;
        int newCapacity = oldCapacity * 2;
        if (newCapacity < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY;
        }
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        this.capacity = newCapacity;
        this.elementData = newElementData;
    }

    @SuppressWarnings("unchecked")
    private void reduce() {
        int oldCapacity = this.capacity;
        E[] oldElementData = this.elementData;
        int newCapacity = oldCapacity / 2;
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        this.capacity = newCapacity;
        this.elementData = newElementData;
    }

}
/* EOF */