package io.ziheng.codinginterviews;

/**
 * 剑指 Offer 面试题 15：二进制中 1 的个数
 *
 * 题目描述：
 * 输入一个整数，输出该数二进制表示中比特位为 1 的个数。
 * 其中，负数使用补码表示。
 *
 * 知识点：["位运算"]
 */
public class NumberOfOneInBinary {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        NumberOfOneInBinary obj = new NumberOfOneInBinary();
        System.out.println(
            "NumberOfOne(0): "
            + obj.numberOfOne(0)
        );
        System.out.println(
            "NumberOfOne(1): "
            + obj.numberOfOne(1)
        );
        System.out.println(
            "NumberOfOne(2): "
            + obj.numberOfOne(2)
        );
        System.out.println(
            "NumberOfOne(-1): "
            + obj.numberOfOne(-1)
        );
    }
    /**
     * 剑指 Offer 面试题 15：二进制中 1 的个数
     *
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return int
     */
    public int numberOfOne(int n) {
        int cnt = 0;
        // 掩码 -> 判断最右边比特位是否为 1
        int mask = 0x01;
        while (n != 0) {
            cnt += n & mask;
            // 无符号右移
            n >>>= 1;
        }
        return cnt;
    }
}
/* EOF */