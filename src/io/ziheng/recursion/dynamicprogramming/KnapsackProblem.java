package io.ziheng.recursion.dynamicprogramming;
/**
 * 背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // int[][] items ->
        // [Weight{int[0]}, Value{int[1]}]
        int[][] items = {
            {1, 6},
            {2, 10},
            {3, 12},
        };
        System.out.println(
            new KnapsackProblem().knapsackProblem(items, 5)
        );
    }
    /**
     * 背包问题。
     *
     * @param items
     * @param capacity
     * @return int 最大价值
     */
    public int knapsackProblem(int[][] items, int capacity) {
        return findMaxValue(items, items.length - 1, capacity);
    }
    /**
     * 以 {@code [0, 1, 2, ..., index]} 的物品，填充容量为 C 的背包，获取最大价值。
     * int[][] items -> [Weight{int[0]}, Value{int[1]}]
     * 
     * @param items
     * @param capacity
     * @return int
     */
    private int findMaxValue(int[][] items, int index, int capacity) {
        /**
         * 递归退出条件：
         * 没有物品可取 or 背包空间不足 -> 价值为 0
         */
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        int maxValueA = 0;
        int maxValueB = 0;
        maxValueA = findMaxValue(items, index - 1, capacity);
        if (capacity >= items[index][0]) {
            maxValueB = items[index][1] + findMaxValue(
                items,
                index - 1,
                capacity - items[index][0]
            );
        }
        return Math.max(maxValueA, maxValueB);
    }
}
/* EOF */