package io.ziheng.queue.leetcode;

/**
 * LeetCode 1700. Number of Students Unable to Eat Lunch
 * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 */
public class NumberOfStudentsUnableToEatLunch {
    public static void main(String[] args) {
        // ...
    }
    public int countStudents(int[] students, int[] sandwiches) {
        int circulars = 0;
        int squares = 0;
        for (int i : students) {
            switch (i) {
            case 0:
                circulars++;
                break;
            case 1:
                squares++;
                break;
            default:
                break;
            }
        }
        for (int i = 0; i < sandwiches.length; i++) {
            switch (sandwiches[i]) {
            case 0:
                circulars--;
                break;
            case 1:
                squares--;
                break;
            default:
                break;
            } 
            if (circulars < 0 || squares < 0) {
                return students.length - i;
            }
        }
        return 0;
    }
}

/* EOF */
