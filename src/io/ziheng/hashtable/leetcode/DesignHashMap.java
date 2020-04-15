package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 706. Design HashMap
 * https://leetcode.com/problems/design-hashmap/
 */
public class DesignHashMap {

    public static void main(String[] args) {
        DesignHashMap aMap = new DesignHashMap();
        int n = DEFAULT_CAPACITY + 10;
        for (int i = 0; i < n; i++) {
            aMap.put(i, i * 10);
        }
        System.out.println(
            aMap.get(9)
        );
        System.out.println(
            aMap.get(1026)
        );
    }

    private class Pair<L, R> {
        public L left;
        public R right;
        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }

    private List<Pair<Integer, Integer>>[] hashTable;
    private static final int DEFAULT_CAPACITY = 1024;

    @SuppressWarnings("unchecked")
    public DesignHashMap() {
        this.hashTable = new List[DEFAULT_CAPACITY];
    }

    public void put(int key, int value) {
        int index = key % DEFAULT_CAPACITY;
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
            hashTable[index].add(new Pair<Integer, Integer>(key, value));
        } else {
            List<Pair<Integer, Integer>> aList = hashTable[index];
            for (Pair<Integer, Integer> node : aList) {
                if (node.left.intValue() == key) {
                    node.right = Integer.valueOf(value);
                    return;
                }
            }
            aList.add(new Pair<Integer, Integer>(key, value));
        }
    }

    public int get(int key) {
        int index = key % DEFAULT_CAPACITY;
        if (hashTable[index] != null) {
            List<Pair<Integer, Integer>> aList = hashTable[index];
            if (aList.size() == 1) {
                return aList.get(0).right.intValue();
            } else {
                for (Pair<Integer, Integer> pair : aList) {
                    if (pair.left.intValue() == key) {
                        return pair.right.intValue();
                    }
                }
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = key % DEFAULT_CAPACITY;
        if (hashTable[index] != null) {
            List<Pair<Integer, Integer>> aList = hashTable[index];
            for (int i = 0; i < aList.size(); i++) {
                if (aList.get(i).left.intValue() == key) {
                    aList.remove(i);
                }
            }
        }
    }
}
/* EOF */