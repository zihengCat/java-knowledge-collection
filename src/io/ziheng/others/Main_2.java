// package io.ziheng.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // 数据读入
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int num = 0;
        int[] arr = null;
        for (int i = 0; i < 2; i++) {
            String line = scanner.nextLine();
            if (i == 0) {
                n = Integer.parseInt(line.split(" ")[0]);
                num = Integer.parseInt(line.split(" ")[1]);
                arr = new int[n];
            } else {
                String[] strArr = line.split(" ");
                for (int j = 0; j < n; j++) {
                    arr[j] = Integer.parseInt(strArr[j]);
                }
            }
        }
        scanner.close();

        // 计算结果
        Main mainClass = new Main();
        mainClass.manipulateArray(0, arr.length, arr, num);
        int result = mainClass.getResult();
        System.out.println(result);
    }

    private int max = -1;
    public int getResult() {
        return max;
    }

    /**
     * 递归法
     */
    public void manipulateArray(int index, int right, int[] nums, int num) {
        if (index >= right) {
            max = Math.max(max, countModeNum(nums));
            return;
        }

        int oldVal = nums[index];

        // 改变当前数组
        nums[index] = nums[index] | num;
        manipulateArray(index + 1, right, nums, num);

        // 不改变当前数组
        nums[index] = oldVal;
        manipulateArray(index + 1, right, nums, num);
    }


    /**
     * 获取众数数量
     *
     * @param nums
     * @return
     */
    public int countModeNum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, 1 + map.get(num));
            }
        }
        return map.values().stream().max(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }
        ).get();
    }

}
/* EOF */