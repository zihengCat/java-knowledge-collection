package io.ziheng.codinginterviews;
/**
 * 剑指 Offer 面试题 11：旋转数组的最小数字
 *
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 知识点：["查找"]
 */
public class MinimalNumberInRotateArray {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        // ...
    }
    public int minimalNumberInRotateArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return minimalNumberInRotateArrayBruteForce(arr);
    }
    private int minimalNumberInRotateArrayBinarySearch(int[] arr) {
        return 0;
    }
    private int minimalNumberInRotateArrayBruteForce(int[] arr) {
        int minimalNumber = arr[0];
        for (int n : arr) {
            if (n < minimalNumber) {
                minimalNumber = n;
            }
        }
        return minimalNumber;
    }
}
/* EOF */