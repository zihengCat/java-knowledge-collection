package io.ziheng.others.leetcode;
/**
 * LeetCode 258. Add Digits
 * https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
    public int addDigits(int num) {
        if (num <= 0) {
            return num;
        }
        // return addDigitsIteratively(num);
        return addDigitsRecursively(num);
    }
    /**
     * Add digits recursively.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param num
     * @return int
     */
    private int addDigitsRecursively(int num) {
        int currentNum = num;
        int digitsCount = 0;
        int nextNum = 0;
        while (currentNum > 0) {
            nextNum += currentNum % 10;
            currentNum /= 10;
            digitsCount++;
        }
        // Recursion ends condition
        if (digitsCount == 1) {
            return num;
        }
        return addDigitsRecursively(nextNum);
    }
    /**
     * Add digits iteratively.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param num
     * @return int
     */
    private int addDigitsIteratively(int num) {
        int digitsCount = 0;
        int nextNum = num;
        do {
            int[] arr = findNextNum(nextNum);
            digitsCount = arr[0];
            nextNum = arr[1];
        } while (digitsCount != 1);
        return nextNum;
    }
    private int[] findNextNum(int num) {
        // Time waste here...
        int[] rArray = new int[2];
        int digitsCount = 0;
        int nextNum = 0;
        while (num > 0) {
            nextNum += num % 10;
            num /= 10;
            digitsCount++;
        }
        rArray[0] = digitsCount;
        rArray[1] = nextNum;
        return rArray;
    }
}
/* EOF */