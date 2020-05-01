package io.ziheng.hashtable.leetcode;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * LeetCode ???. First Unique Number
 */
public class FirstUniqueNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, };
        FirstUniqueNumber obj = new FirstUniqueNumber(nums);
        System.out.println(
            obj.showFirstUnique()
        );
        obj.add(1);
        obj.add(2);
        obj.add(3);
        System.out.println(
            obj.showFirstUnique()
        );
    }

    private Map<Integer, Integer> aMap;
    private List<Integer> aList;

    public FirstUniqueNumber(int[] nums) {
        this.aMap = new HashMap<>();
        this.aList = new ArrayList<>();
        for (int num : nums) {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        return aList.isEmpty() ? -1 : aList.get(0);
    }
    
    public void add(int value) {
        if (!aMap.containsKey(value)) {
            aList.add(value);
            aMap.put(value, aList.size() - 1);
        } else {
            int index = aMap.get(value);
            aList.remove(index);
            aMap.put(value, null);
        }
    }
}
/* EOF */