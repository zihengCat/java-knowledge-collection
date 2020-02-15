package io.ziheng.graph.lookup;

import io.ziheng.graph.lookup.LookUpTable;

import java.util.TreeSet;

public class RedBlackTreeSetLookUpTable<E> implements LookUpTable<E> {
    private TreeSet<E> treeSet;
    private class TreeSetIterator implements LookUpTable.Iterator<E> {
        private java.util.Iterator<E> innerIterator;
        public TreeSetIterator() {
            this.innerIterator = treeSet.iterator();
        }
        @Override
        public E next() {
            return innerIterator.next();
        }
        @Override
        public boolean hasNext() {
            return innerIterator.hasNext();
        }
        @Override
        public void remove() {
            innerIterator.remove();
        }
    }
    public RedBlackTreeSetLookUpTable() {
        this.treeSet = new TreeSet<E>();
    }
    @Override
    public void add(E e) {
        treeSet.add(e);
    }
    @Override
    public boolean contains(E e) {
        return treeSet.contains(e);
    }
    @Override
    public boolean remove(E e) {
        return treeSet.remove(e);
    }
    @Override
    public int size() {
        return treeSet.size();
    }
    @Override
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }
    @Override
    public void clear() {
        treeSet.clear();
    }
    @Override
    public Iterator<E> iterator() {
        return new TreeSetIterator();
    }
}
/* EOF */