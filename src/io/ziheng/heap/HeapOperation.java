package io.ziheng.heap;
public interface HeapOperation<E> {
    void add(E e);
    E peek();
    E extract();
    int size();
    boolean isEmpty();
    void clear();
}
/* EOF */