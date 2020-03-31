package io.ziheng.list.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 1352. Product of the Last K Numbers
 * https://leetcode.com/problems/product-of-the-last-k-numbers/
 */
public class ProductOfTheLastKNumbers {
    private List<Integer> list;
    private int size;

    public ProductOfTheLastKNumbers() {
        this.list = new ArrayList<>();
        this.size = 0;
    }
    
    public void add(int num) {
        list.add(num);
        size++;
    }
    
    public int getProduct(int k) {
        /**
         * 1, 2, 3, 4, 5,
         * k = 2
         * start = 5 - 2 = 3
         */
        int index = size - k;
        int result = 1;
        while (index < size) {
            result *= list.get(index);
            index++;
        }
        return result;
    }
}
/* EOF */