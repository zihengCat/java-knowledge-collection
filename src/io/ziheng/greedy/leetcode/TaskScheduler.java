package io.ziheng.greedy.leetcode;

import java.util.Arrays;

/**
 * LeetCode 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 */
class TaskScheduler {
    public static void main(String[] args) {
        TaskScheduler obj = new TaskScheduler();
        char[] tasks = new char[]{
            'A','A','A','A','A','A','B','C','D','E','F','G',
        };
        int n = 2;
        obj.leastInterval(tasks, n);
    }
    public int leastInterval(char[] tasks, int n) {
        // Detect edge cases
        if (tasks == null || tasks.length == 0
            || n < 0) {
            return 0;
        }
        // Count occurrences of tasks
        int[] arr = new int[26];
        for (char task : tasks) {
            arr[task - 'A']++;
        }
        // Put the most frequent task to the end
        Arrays.sort(arr);
        // Ext is the number of appending numbers in last "round"
        int ext = 0;
        for (int i = 25; i >= 0; i--) {
            if (arr[i] == arr[25]) {
                ext++;
            } else {
                break;
            }
        }
        // Frame * Gap + Ext
        return Math.max(tasks.length, (arr[25] - 1) * (n + 1) + ext);
    }
}
/* EOF */