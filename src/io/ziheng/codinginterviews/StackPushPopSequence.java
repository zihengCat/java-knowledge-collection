package io.ziheng.codinginterviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 31：栈的压入、弹出序列
 *
 * 题目描述：
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如：
 * - 序列 [1,2,3,4,5] 是某栈的压入顺序
 * - 序列 [4,5,3,2,1] 是该压栈序列对应的一个弹出序列
 * - 序列 [4,3,5,1,2] 不可能是该压栈序列的弹出序列
 * 注意：输入两个序列长度相等
 *
 * 知识点：["栈"]
 */
public class StackPushPopSequence {
    public static void main(String[] args) {
        StackPushPopSequence obj = new StackPushPopSequence();
        int[] pushSeq = new int[]{1, 2, 3, 4, 5, };
        int[] popSeq = new int[]{4, 3, 5, 1, 2, };
        System.out.println(
            obj.isPopOrder(pushSeq, popSeq)
        );
    }
    /**
     * 剑指 Offer 面试题 31：栈的压入、弹出序列
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param pushSeq
     * @param popSeq
     * @return boolean
     */
    public boolean isPopOrder(int[] pushSeq,int [] popSeq) {
        if (pushSeq == null
            || popSeq == null
            || pushSeq.length != popSeq.length) {
            return false;
        }
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0, j = 0; i < pushSeq.length; i++) {
            stack.push(pushSeq[i]);
            while (!stack.isEmpty() &&
                stack.peek().intValue() == popSeq[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
/* EOF */