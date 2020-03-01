package io.ziheng.heap;
public interface HeapOperator<E> {
    void insert(E e);
    E extract();
    E peek();
    int size();
    boolean isEmpty();
    void clear();
}
/* EOF */