package io.ziheng.hashtable.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 705. Design HashSet
 * https://leetcode.com/problems/design-hashset/
 */
public class DesignHashSet {

    private Set<Integer> aSet;

    public DesignHashSet() {
        this.aSet = new HashSet<>();
    }

    public void add(int key) {
        aSet.add(key);
    }

    public void remove(int key) {
        aSet.remove(key);
    }

    public boolean contains(int key) {
        return aSet.contains(key);
    }
}
/* EOF */