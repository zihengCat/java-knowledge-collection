package io.ziheng.hashtable;

import io.ziheng.hashtable.Map;

public class HashMap<K,V> { // implements Map<K,V> {

    /* 测试代码 */
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String,String>();
        System.out.println(hashMap.getThreshold());
    }

    /* 数组默认长度 */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    /* 数组最大长度 */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /* 哈希桶数组 */
    private Node<K,V>[] table;

    /* 桶数组下限长度 */
    private int threshold;

    /* 哈希表实际存放键值对数量 */
    private int size;

    /**
     * 无参（默认容量）构造。
     *
     * @param void
     */
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }
    /**
     * 指定容量构造。
     *
     * @param initialCapacity
     */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                "[ERROR]: Illegal initial capacity: " +
                initialCapacity
            );
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        this.threshold = tableSizeFor(initialCapacity);
        this.table = (Node<K,V>[])new Node[threshold];
        this.size = 0;
    }
    /**
     * 获取当前哈希表最大容量。
     *
     * @param void
     * @return int
     */
    public int getThreshold() {
        if (threshold != table.length) {
            throw new RuntimeException("[ERROR]: In threshold");
        }
        return threshold;
    }
    /**
     * 散列函数，计算键哈希值。
     *
     * @param key
     * @return int
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            int h = key.hashCode();
            return h ^ (h >>> 16);
        }
    }
    /**
     * 功能函数，计算哈希表下限容量。
     *
     * @param capacity
     * @return int
     */
    private int tableSizeFor(int capacity) {
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 :
            (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : (n + 1);
    }
    /**
     * Map.Entry Implementation: Singly LinkedList Node
     */
    private class Node<K,V> implements Map.Entry<K,V> {
        private int hash;
        private K key;
        private V value;
        private Node<K,V> next;
        public Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public K getKey(){
            return key;
        }
        public V getValue() {
            return value;
        }
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        @Override
        public String toString() {
            return String.format(
                "%s=%s", key.toString(), value.toString()
            );
        }
    }
}
/* EOF */
