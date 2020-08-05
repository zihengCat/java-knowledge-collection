package io.ziheng.others.leetcode;
/**
 * LeetCode 342. Power of Four
 * https://leetcode.com/problems/power-of-four/
 */
public class PowerOfFour {
    public static void main(String[] args) {
        PowerOfFour obj = new PowerOfFour();
        System.out.println(
            "isPowerOfFour(4): "
            + obj.isPowerOfFour(4)
        );
        System.out.println(
            "isPowerOfFour(5): "
            + obj.isPowerOfFour(5)
        );
        System.out.println(
            "isPowerOfFour(16): "
            + obj.isPowerOfFour(16)
        );
        System.out.println(
            "isPowerOfFour(-16): "
            + obj.isPowerOfFour(-16)
        );
        System.out.println(
            "isPowerOfFour(1): "
            + obj.isPowerOfFour(1)
        );
        System.out.println(
            "isPowerOfFour(8): "
            + obj.isPowerOfFour(8)
        );
    }
    /**
     * Power of Four
     *
     * @param num
     * @return boolean
     */
    public boolean isPowerOfFour(int num) {
        // return isPowerOfFourIteritively(num);
        return isPowerOfFourRecursively(num);
    }
    public boolean isPowerOfFourRecursively(int num) {
        if (num == 0) {
            return false;
        }
        if (num % 4 != 0) {
            return num == 1;
        }
        if (num == 4) {
            return true;
        }
        return isPowerOfFourRecursively(num / 4);
    }
    public boolean isPowerOfFourIteritively(int num) {
        if (num == 0) {
            return false;
        }
        int remainder = num % 4;
        while (num != 1) {
            remainder = num % 4;
            if (remainder != 0) {
                return false;
            }
            num /= 4;
        }
        return true;
    }
}
/* EOF */