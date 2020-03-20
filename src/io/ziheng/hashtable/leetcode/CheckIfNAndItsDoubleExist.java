package io.ziheng.hashtable.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1346. Check If N and Its Double Exist
 * https://leetcode.com/problems/check-if-n-and-its-double-exist/
 */
public class CheckIfNAndItsDoubleExist {
    public boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        Set<Integer> aSet = new HashSet<>();
        int zeroCount = 0;
        for (int num : arr) {
            if (num == 0) {
                zeroCount++;
            } else {
                aSet.add(num);
            }
        }
        // 排除 0 的 case
        if (zeroCount > 1) {
            return true;
        }
        for (int num : arr) {
            if (aSet.contains(2 * num)) {
                return true;
            }
        }
        return false;
    }
}
/* EOF */