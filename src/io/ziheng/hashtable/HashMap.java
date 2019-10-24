package io.ziheng.hashtable;

import io.ziheng.hashtable.Map;

public class HashMap<K,V> { // implements Map<K,V> {

    public static void main(String[] args) {
        System.out.println(new HashMap<String,String>());
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public HashMap() {
        // ...
    }

    /* Map.Entry Implementation: Singly LinkedList Node */
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
    /**
     * 散列函数，计算键哈希值。
     *
     * @param key
     * @return int
     */
    private static int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            int h = key.hashCode();
            return h ^ (h >>> 16);
        }
    }

}
/* EOF */
