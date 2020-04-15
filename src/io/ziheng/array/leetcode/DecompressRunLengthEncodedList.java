package io.ziheng.array.leetcode;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1313. Decompress Run-Length Encoded List
 * https://leetcode.com/problems/decompress-run-length-encoded-list/
 */
public class DecompressRunLengthEncodedList {
    public int[] decompressRLElist(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        List<Integer> aList = new ArrayList<>();
        for (int i = 0; i < nums.length / 2; i++) {
            int freq = nums[2 * i];
            int val = nums[2 * i + 1];
            for (int j = 0; j < freq; j++) {
                aList.add(val);
            }
        }
        int[] resultArray = new int[aList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = aList.get(i);
        }
        return resultArray;
    }
}
/* EOF */