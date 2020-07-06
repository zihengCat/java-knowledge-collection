package io.ziheng.bitmanipulation.leetcode;
/**
 * LeetCode 461. Hamming Distance
 * https://leetcode.com/problems/hamming-distance/
 */
public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance obj = new HammingDistance();
        int x = 2;
        int y = 4;
        System.out.println(obj.hammingDistance(x, y));
    }
    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param x
     * @param y
     * @return int
     */
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int mask = 0x01;
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((z & mask) > 0) {
                cnt++;
            }
            z >>>= 1;
        }
        return cnt;
    }
}
/* EOF */