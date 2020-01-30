package io.ziheng.list;
public interface List<E> {
    int size();
    boolean isEmpty();
    boolean contains(E element);
    int indexOf(E element);
    void clear();
    void addFirst(E element);
    void addLast(E element);
    void add(int index, E element);
    E removeFirst();
    E removeLast();
    E remove(int index);
    E get(int index);
    E set(int index, E element);
    String toString();
}
/* EOF */