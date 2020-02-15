package io.ziheng.graph.lookup;

import io.ziheng.graph.lookup.LookUpTable;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedListLookUpTable<E> implements LookUpTable<E> {
    private LinkedList<E> linkedList;
    private class LinkedListIterator implements LookUpTable.Iterator<E> {
        private int nextIndex;
        public LinkedListIterator() {
            this.nextIndex = 0;
        }
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = linkedList.get(nextIndex);
            nextIndex++;
            return element;
        }
        @Override
        public boolean hasNext() {
            return nextIndex < linkedList.size();
        }
        @Override
        public void remove() {
            // ...
        }
    }
    public LinkedListLookUpTable() {
        this.linkedList = new LinkedList<E>();
    }
    @Override
    public void add(E e) {
        linkedList.add(e);
    }
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }
    @Override
    public boolean remove(E e) {
        return linkedList.remove(e);
    }
    @Override
    public int size() {
        return linkedList.size();
    }
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    @Override
    public void clear() {
        linkedList.clear();
    }
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
}
/* EOF */