package io.ziheng.array.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 1389. Create Target Array in the Given Order
 * https://leetcode.com/problems/create-target-array-in-the-given-order/
 */
public class CreateTargetArrayInTheGivenOrder {
    public int[] createTargetArray(int[] nums, int[] index) {
        if (nums == null || index == null || nums.length != index.length) {
            return new int[0];
        }
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            resultList.add(index[i], nums[i]);
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
/* EOF */