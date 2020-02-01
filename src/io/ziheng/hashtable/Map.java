package io.ziheng.hashtable;
public interface Map<K, V> {
    /**
     * Map Entry -> key-value pair
     */
    public interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);
        // boolean equals(Entry<K,V> entry);
    }
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    void clear();
    int getKeyHash(K key);
    int getKeyIndex(K key);
}
/* EOF */