package io.ziheng.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 数据读入
        int n, k, left, right;
        int[] arr = new int[4];
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            arr[i] = scanner.nextInt();
            i++;
        }
        scanner.close();

        // 数组长度
        n = arr[0];
        // 被整除数
        k = arr[1];
        // 左边界
        left = arr[2];
        // 右边界
        right = arr[3];

        // 计算结果
        Main mainClass = new Main();
        mainClass.k = k;
        mainClass.countNum(0, n, left, right, new ArrayList<Integer>());
        int result = mainClass.getResult();
        System.out.println(result);
    }

    // 计数器
    private int cnt = 0;
    public int k;
    public int getResult() {
        return cnt;
    }
    /**
     * 递归解法
     *
     * @param index
     * @param n
     * @param left
     * @param right
     * @param list
     */
    public void countNum(int index, int n, int left, int right, List<Integer> list) {
        // 递归终止条件
        if (index >= n) {
            if (isVaild(list, k)) {
                cnt++;
            }
            return;
        }
        // 左右端点均可取
        for (int num = left; num <= right; num++) {
            list.add(num);
            countNum(index + 1, n, left, right, list);
            // 恢复状态
            list.remove(list.size() - 1);
        }
    }
    /**
     * 判断是否符合条件
     *
     * @param list
     * @param k
     * @return
     */
    private boolean isVaild(List<Integer> list, int k) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum % k == 0;
    }

}
/* EOF */