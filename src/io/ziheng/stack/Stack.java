package io.ziheng.stack;
public interface Stack<E> {
    public void push(E e);
    public E pop();
    public E top();
    public int size();
    public boolean isEmpty();
    public void clear();
}
/* EOF */
