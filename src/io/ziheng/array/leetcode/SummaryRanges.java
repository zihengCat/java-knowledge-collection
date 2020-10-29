package io.ziheng.array.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 228. Summary Ranges
 * https://leetcode.com/problems/summary-ranges/
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges obj = new SummaryRanges();
        int[] nums = new int[]{
            0,1,2,4,5,7,
        };
        System.out.println(
            obj.summaryRanges(nums)
        );
    }
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        if (nums.length == 1) {
            List<String> rList = new LinkedList<>();
            rList.add(""+nums[0]);
            return rList;
        }
        List<String> rList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int sNum = nums[i];
            while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
                i++;
            }
            if (nums[i] == sNum) {
                rList.add("" + sNum);
            } else {
                rList.add("" + sNum + "->" + nums[i]);
            }
        }
        return rList;
    }
}

/* EOF */