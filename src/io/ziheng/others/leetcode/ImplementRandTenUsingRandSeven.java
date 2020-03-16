package io.ziheng.others.leetcode;

import java.util.Random;

public class ImplementRandTenUsingRandSeven {
    public int rand10() {
        int index = Integer.MAX_VALUE;
        while (index > 40) {
            int row = rand7();
            int col = rand7();
            index = 7 * (row - 1) + col;
        }
        return 1 + (index - 1) % 10;
    }
    private int rand7() {
        Random r = new Random();
        return r.nextInt(7) + 1;
    }
}
/* EOF */