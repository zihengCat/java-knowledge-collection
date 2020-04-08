package io.ziheng.codinginterviews;
/**
 * 剑指 Offer 面试题 03：二维数组中的查找
 *
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 知识点：["查找", "数组"]
 */
public class LookupInATwoDimensionalArray {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        LookupInATwoDimensionalArray obj = new LookupInATwoDimensionalArray();
        int[][] array = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30},
        };
        int target = 14;
        System.out.println(
            obj.find(target, array)
        );
        target = 128;
        System.out.println(
            obj.find(target, array)
        );
    }
    public boolean find(int target, int[][] array) {
        for (int i = 0, j = array[i].length - 1;
            i >= 0 && i < array.length && j >= 0 && j < array[i].length;
            /* ... */) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                j--;
            } else if (array[i][j] < target) {
                i++;
            }
        }
        return false;
    }
}
/* EOF */