package io.ziheng.graph.lookup;
public interface LookUpTable<E> {
    public interface Iterator<T> {
        boolean hasNext();
        T next();
        void remove();
    }
    void add(E e);
    boolean remove(E e);
    boolean contains(E e);
    int size();
    boolean isEmpty();
    void clear();
    Iterator<E> iterator();
}
/* EOF */