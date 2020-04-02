package io.ziheng.array.leetcode;
/**
 * LeetCode 739. Daily Temperatures
 * https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return new int[0];
        }
        int[] resultArray = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            resultArray[i] = findNext(i, T);
        }
        return resultArray;
    }
    private int findNext(int index, int[] arr) {
        int target = arr[index];
        int cnt = 0;
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                return cnt;
            }
            cnt++;
        }
        return 0;
    }
}
/* EOF */