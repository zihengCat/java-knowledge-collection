package io.ziheng.hashtable.leetcode;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 380. Insert Delete GetRandom O(1)
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
public class InsertDeleteGetRandom {

    private Random random;
    private Set<Integer> set;

    public InsertDeleteGetRandom() {
        this.random = new Random();
        this.set = new HashSet<>();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        Integer[] arr = set.toArray(new Integer[set.size()]);
        return arr[random.nextInt(set.size())];
    }
}
/* EOF */