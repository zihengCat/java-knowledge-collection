package io.ziheng.hashtable;

import io.ziheng.hashtable.Map;

public class HashMap<K, V> implements Map<K, V> {
    /**
     * Map.Entry Implementation ->
     * Singly LinkedList Node
     */
    private class Node<K, V> implements Map.Entry<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;
        public Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public int getHash() {
            return hash;
        }
        public int setHash(int newHash) {
            int oldHash = hash;
            this.hash = newHash;
            return oldHash;
        }
        public Node<K, V> getNext() {
            return next;
        }
        public void setNext(Node<K, V> next) {
            this.next = next;
        }
        @Override
        public K getKey(){
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }
        @Override
        public String toString() {
            return String.format(
                "%s=%s", key.toString(), value.toString()
            );
        }
    }
    public static void main(String[] args) {
        testHashMap(new HashMap<Integer, Integer>());
    }
    /* 测试代码 */
    public static void testHashMap(Map<Integer, Integer> map) {
        /* Test -> Map.put() */
        System.out.println("Map.size(): " + map.size());
        for (int i = 0; i < 100; ++i) {
            map.put(i, i * 10);
        }
        System.out.println("Map.size(): " + map.size());

        /* Test -> Map.containsKey() */
        System.out.println("Map.containsKey(64): " + map.containsKey(64));
        System.out.println("Map.containsKey(640): " + map.containsKey(640));

        /* Test -> Map.containsValue() */
        System.out.println("Map.containsValue(64): " + map.containsValue(64));
        System.out.println("Map.containsValue(640): " + map.containsValue(640));

        /* Test -> Map.remove() */
        for (int i = 0; i < 100; ++i) {
            //System.out.printf("{%d = %d}" + System.lineSeparator(),
            //    i, map.getKeyIndex(i)
            //);
            map.remove(i);
        }
        System.out.println("Map.size(): " + map.size());

        /* Test -> Map.get() */
        System.out.println("Map.get(48): " + map.get(48));
        System.out.println("Map.getKeyIndex(48): " + map.getKeyIndex(48));
        System.out.println("Map.get(64): " + map.get(64));
        System.out.println("Map.getKeyIndex(64): " + map.getKeyIndex(64));
        System.out.println("Map.get(80): " + map.get(80));
        System.out.println("Map.getKeyIndex(80): " + map.getKeyIndex(80));
        System.out.println("Map.remove(64): " + map.remove(64));
        System.out.println("Map.get(64): " + map.get(64));

        /* Test -> Map.clear() */
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        System.out.println("Map.size(): " + map.size());
        map.clear();
        System.out.println("Map.size(): " + map.size());

    }

    /* 哈希桶数组默认长度 */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    /* 哈希桶数组最大长度 */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /* 哈希桶数组 */
    private Node<K, V>[] table;

    /* 哈希桶数组配额 */
    private int threshold;

    /* 哈希表存放元素数量 */
    private int size;

    /* ---------------- Public Operations ---------------- */
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
        this.table = (Node<K, V>[])new Node[threshold];
        this.size = 0;
    }
    /**
     * 返回哈希表存放元素数量。
     *
     * @param void
     * @return int
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * 判断哈希表是否为空。
     *
     * @param void
     * @return int
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 根据键获取值。
     *
     * @param key
     * @return V
     */
    @Override
    public V get(K key) {
         Node<K, V> node = getNode(hash(key), key);
         return node == null ? null : node.getValue();
    }
    /**
     * 添加或更新键值对至哈希表中。
     *
     * @param key
     * @param value
     * @return V
     */
    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }
    /**
     * 移除哈希表中的键值对。
     *
     * @param key
     * @return V
     */
    @Override
    public V remove(K key) {
        Node<K, V> node = removeNode(hash(key), key);
        return node == null ? null : node.getValue();
    }
    /**
     * 清空哈希表。
     *
     * @param void
     * @return void
     */
    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < threshold; ++i) {
                freeNode(table[i]);
                table[i] = null;
            }
            size = 0;
        }
    }
    /**
     * 检查哈希表中是否存在目标键。
     *
     * @param key
     * @return boolean
     */
    public boolean containsKey(K key) {
        return getNode(hash(key), key) != null;
    }
    /**
     * 检查哈希表中是否存在目标值。
     *
     * @param value
     * @return boolean
     */
    public boolean containsValue(V value) {
        if (table != null && size > 0) {
            Node<K,V> currentNode;
            V nodeValue;
            for (int i = 0; i < threshold; ++i) {
                if ((currentNode = table[i]) != null &&
                   ((nodeValue = currentNode.getValue()) == value ||
                    (nodeValue != null && value.equals(nodeValue)))) {
                    return true;
                }
                if ((currentNode = currentNode.getNext()) != null) {
                    do {
                        if ((nodeValue = currentNode.getValue()) == value ||
                            (nodeValue != null && value.equals(nodeValue))) {
                            return true;
                        }
                    } while ((currentNode = currentNode.getNext()) != null);
                }
            }
        }
        return false;
    }
    public int getKeyHash(K key) {
        return hash(key);
    }
    public int getKeyIndex(K key) {
        return (table.length - 1) & hash(key);
    }
    /* ---------------- Private Operations ---------------- */
    /**
     * 哈希表扩容操作（扩容2倍）。
     * @param void
     * @return {@code Node<K,V>[]}
     */
    private Node<K,V>[] resize() {
        // TODO
        return null;
    }
    private void freeNode(Node<K, V> node) {
        if (node == null) {
            return;
        }
        node.setHash(-1);
        node.setValue(null);
        node.setNext(null);
    }
    private Node<K,V> removeNode(int hash, K key) {
        Node<K,V>[] tab;
        Node<K,V> firstNode;
        int len;
        int index;
        if ((tab = table) != null && (len = tab.length) > 0 &&
            (firstNode = tab[index = (len - 1) & hash]) != null) {
            Node<K,V> currentNode = null;
            Node<K,V> previousNode = null;
            K nodeKey;
            V nodeValue;
            /* 检查链表头节点 */
            if (firstNode.getHash() == hash &&
               ((nodeKey = firstNode.getKey()) == key ||
                (nodeKey != null && key.equals(nodeKey)))) {
                currentNode = firstNode;
                previousNode = null;
            } else if (firstNode.getNext() != null) {
                previousNode = firstNode;
                currentNode = firstNode.getNext();
                do {
                    if (currentNode.getHash() == hash &&
                       ((nodeKey = currentNode.getKey()) == key ||
                        (nodeKey != null && key.equals(nodeKey)))) {
                        break;
                    }
                    previousNode = currentNode;
                } while ((currentNode = currentNode.getNext()) != null);
            }
            if (currentNode != null) {
                if (currentNode == firstNode) {
                    tab[index] = currentNode.getNext();
                    currentNode.setNext(null);
                } else {
                    previousNode.setNext(currentNode.getNext());
                    currentNode.setNext(null);
                }
                size--;
                return currentNode;
            }
        }
        return null;
    }
    private V putVal(int hash, K key, V value) {
        Node<K,V>[] tab = table;
        int len = tab.length;
        Node<K,V> node;
        int index;
        /* 如果哈希桶数组为空：新建数组 */
        if (tab == null || len == 0) {
            System.out.println("resize()");
            // len = (tab = resize()).length;
        }
        /* 哈希桶目标位置为空：直接插入新节点 */
        if ((node = tab[index = (len - 1) & hash]) == null) {
            tab[index] = new Node<K,V>(hash, key, value, null);
        }
        /**
         * 哈希桶目标位置不为空 ->
         * 哈希冲突，使用拉链法插入新节点
         */
        else {
            Node<K,V> currentNode;
            K nodeKey;
            /**
             * 哈希相同 -> 更新节点
             * 哈希不同 -> 链至尾部
             */
            if (node.getHash() == hash &&
               ((nodeKey = node.getKey()) == key ||
                (key != null && key.equals(nodeKey)))) {
                currentNode = node;
            } else {
                for (int binCount = 0; ; ++binCount) {
                    currentNode = node.getNext();
                    if (currentNode == null) {
                        node.setNext(new Node<K,V>(hash, key, value, null));
                        break;
                    }
                    if (node.getHash() == hash &&
                       ((nodeKey = node.getKey()) == key ||
                        (key != null && key.equals(nodeKey)))) {
                        break;
                    }
                    node = currentNode;
                }
            }
            /* existing mapping for key */
            if (currentNode != null) {
                V oldValue = currentNode.getValue();
                currentNode.setValue(value);
                return oldValue;
            }
        }
        size++;
        if (size >= threshold) {
            //System.out.println("resize()");
            // resize();
        }
        return null;
    }
    /**
     * 根据哈希与键获取目标节点，不存在则返回{@code null}。
     *
     * @param hash
     * @param key
     * @return {@code Node<K,V>}
     */
    private Node<K,V> getNode(int hash, K key) {
        Node<K, V>[] tab;
        Node<K, V> first;
        Node<K, V> e;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            /**
             * always check first node
             */
            if (first.getHash() == hash &&
                ((k = first.getKey()) == key ||
                (key != null && key.equals(k)))) {
                return first;
            }
            if ((e = first.getNext()) != null) {
                do {
                    if (e.getHash() == hash &&
                        ((k = e.getKey()) == key ||
                         (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.getNext()) != null);
            }
        }
        return null;
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
     * 哈希散列函数，计算键的哈希值。
     *
     * @param key
     * @return int
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            int hash = key.hashCode();
            /* 扰动 */
            return hash ^ (hash >>> 16);
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
}
/* EOF */