
/**
 * LeetCode 1800. Maximum Ascending Subarray Sum
 * https://leetcode.com/problems/maximum-ascending-subarray-sum/
 */
class MaximumAscendingSubarraySum {
	public static void main(String[] args) {}
        // ...
    }
    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length== 0) {
            return 0;
        }
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i == nums.length - 1 || nums[i] >= nums[i + 1]) {
                res = Math.max(res, sum);
                sum = 0;
            }
        }
        return res;
    }
}

/* EOF */
