package io.ziheng.search.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 365. Water and Jug Problem
 * https://leetcode.com/problems/water-and-jug-problem/
 */
public class WaterAndJugProblem {
    public boolean canMeasureWaterMathWay(int x, int y, int z) {
        if (z < 0 || x + y < z) {
            return false;
        }
        if (x == z || y == z || x + y == z) {
            return true;
        }
        return z % greatestCommonDivisor(x, y) == 0;
    }
    private int greatestCommonDivisor(int x, int y) {
        while (y > 0) {
            int t = y;
            y = x % y;
            x = t;
        }
        return x;
    }
    /**
     * 广度优先搜索 - BFS。
     *
     * @param x
     * @param y
     * @param z
     * @return boolean
     */
    public boolean canMeasureWater(int x, int y, int z) {
        // 处理异常情况
        if (z < 0 || x + y < z) {
            return false;
        }
        Queue<Integer[]> queue = new LinkedList<>();
        Set<Integer[]> visited = new HashSet<>();
        queue.offer(new Integer[]{0, 0, });
        visited.add(new Integer[]{0, 0, });
        while (!queue.isEmpty()) {
            Integer[] item = queue.poll();
            int a = item[0];
            int b = item[1];
            // 是否搜到
            if (a == z || b == z || a + b == z) {
                return true;
            }
            List<Integer[]> states = getNextStates(a, b, x, y);
            for (Integer[] state : states) {
                if (!visited.contains(state)) {
                    visited.add(state);
                    queue.offer(state);
                }
            }
        }
        return false;
    }
    private List<Integer[]> getNextStates(int a, int b, int x, int y) {
        List<Integer[]> states = new LinkedList<>();
        // 清空
        states.add(new Integer[]{0, b});
        states.add(new Integer[]{a, 0});
        // 填满
        states.add(new Integer[]{x, b});
        states.add(new Integer[]{a, y});
        // a -> b, a + b
        if (a + b < y) {
            states.add(new Integer[]{0, a + b});
        } else {
            states.add(new Integer[]{a + b - y, y});
        }
        // b -> a, a + b
        if (a + b < x) {
            states.add(new Integer[]{a + b, 0});
        } else {
            states.add(new Integer[]{x, a + b - y});
        }
        return states;
    }
}
/* EOF */