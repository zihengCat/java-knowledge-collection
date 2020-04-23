package io.ziheng.stack.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 682. Baseball Game
 * https://leetcode.com/problems/baseball-game/
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        Deque<Integer> aStack = new LinkedList<>();
        for (String op : ops) {
            if (op.equals("+")) {
                Integer pointA = aStack.pop();
                Integer pointB = aStack.pop();
                aStack.push(pointB);
                aStack.push(pointA);
                aStack.push(pointA + pointB);
            } else if (op.equals("D")) {
                aStack.push(aStack.peek() * 2);
            } else if (op.equals("C")) {
                aStack.pop();
            } else {
                aStack.push(Integer.valueOf(op));
            }
        }
        int sumValue = 0;
        while (!aStack.isEmpty()) {
            sumValue += aStack.pop();
        }
        return sumValue;
    }
}
/* EOF */