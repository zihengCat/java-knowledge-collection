package io.ziheng.recursion.dynamicprogramming.leetcode;
/**
 * LeetCode 1025. Divisor Game
 * https://leetcode.com/problems/divisor-game/
 */
public class DivisorGame {
    public static void main(String[] args) {
        DivisorGame obj = new DivisorGame();
        System.out.println(
            obj.divisorGame(2)
        );
        System.out.println(
            obj.divisorGame(3)
        );
    }
    private static final int ALICE = 1;
    private static final int BOB = 2;
    private boolean alice = true;
    private boolean bob = true;
    public boolean divisorGame(int N) {
        if (N < 0) {
            return false;
        }
        divisorGame0(N, ALICE);
        return alice;
    }
    private void divisorGame0(int N, int people) {
        boolean flag = false;
        for (int i = 1; i < N; i++) {
            if (N % i == 0) {
                flag = true;
                divisorGame0(
                    N - i,
                    people == ALICE ? BOB : ALICE
                );
            }
        }
        if (flag == false) {
            if (people == ALICE) {
                alice = false;
            } else if (people == BOB) {
                bob = false;
            }
        }
    }
}
/* EOF */