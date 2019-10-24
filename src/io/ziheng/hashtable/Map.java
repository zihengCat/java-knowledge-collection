package io.ziheng.hashtable;
public interface Map<K,V> {
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    void clear();
    /* Map Entry: key-value pair */
    public interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        // boolean equals(Entry<K,V> entry);
    }
}
/* EOF */
