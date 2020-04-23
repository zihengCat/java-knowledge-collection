package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 414. Third Maximum Number
 * https://leetcode.com/problems/third-maximum-number/
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        ThirdMaximumNumber obj = new ThirdMaximumNumber();
        int[] nums = {2, 2, 3, 1, };
        System.out.println(
            obj.thirdMax(nums)
        );
    }
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return thirdMaxSorting(nums);
    }
    private int thirdMaxSorting(int[] nums) {
        Set<Integer> aSet = new HashSet<>();
        for (int n : nums) {
            aSet.add(n);
        }
        System.out.println(aSet.size());
        Integer[] newArray = aSet.toArray(new Integer[aSet.size()]);
        Arrays.sort(newArray);
        if (newArray.length < 3) {
            return newArray[newArray.length - 1];
        } else {
            return newArray[newArray.length - 3];
        }
    }
}
/* EOF */