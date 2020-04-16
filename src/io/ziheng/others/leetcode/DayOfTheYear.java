package io.ziheng.others.leetcode;
/**
 * LeetCode 1154. Day of the Year
 * https://leetcode.com/problems/day-of-the-year/
 */
public class DayOfTheYear {
    private static int[] monthDays = new int[]{
        29,
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
    };
    public int dayOfYear(String date) {
        if (date == null || date.length() == 0) {
            return 0;
        }
        String[] arr = date.split("-");
        int year = Integer.valueOf(arr[0]);
        int month = Integer.valueOf(arr[1]);
        int day = Integer.valueOf(arr[2]);
        int dayOfYear = 0;
        for (int i = 1; i < month; i++) {
            if (i == 2 && isLeapYear(year)) {
                dayOfYear += monthDays[0];
            } else {
                dayOfYear += monthDays[i];
            }
        }
        return dayOfYear + day;
    }
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0)
            || (year % 400 == 0);
    }
}
/* EOF */