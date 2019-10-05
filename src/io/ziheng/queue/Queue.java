package io.ziheng.queue;
public interface Queue<E> {
    public void enqueue(E e);
    public E dequeue();
    public E peek();
    public int size();
    public boolean isEmpty();
    public void clear();
}
/* EOF */
