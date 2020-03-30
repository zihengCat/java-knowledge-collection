package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 292. Nim Game
 * https://leetcode.com/problems/nim-game/
 */
public class NimGame {
    private static int pA = 0;
    private static int pB = 1;
    private boolean[] result = new boolean[2];
    public boolean canWinNim(int n) {
        canWinNim0(n, pA);
        return result[0];
    }
    private void canWinNim0(int n, int people) {
        if (n < 4) {
            result[people] = true;
            return;
        }
        canWinNim0(n - 1, people == pA ? pB : pA);
        canWinNim0(n - 2, people == pA ? pB : pA);
        canWinNim0(n - 3, people == pA ? pB : pA);
    }
}
/* EOF */