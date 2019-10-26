package io.ziheng.hashtable;
public interface Map<K,V> {

    /* Normal Operations */
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    void clear();

    /* Test Operations */
    int getKeyHash(K key);
    int getKeyIndex(K key);

    /* Map Entry: key-value pair */
    public interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        // boolean equals(Entry<K,V> entry);
    }
}
/* EOF */
