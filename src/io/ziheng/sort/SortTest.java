package io.ziheng.sort;

import io.ziheng.sort.SelectionSort;
import io.ziheng.sort.InsertionSort;
import io.ziheng.sort.BubbleSort;
import io.ziheng.sort.MergeSort;
import io.ziheng.sort.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
    public static void main(String[] args) {
        System.out.println("SelectionSort(): ");
        testSort(new SelectionSort());
        System.out.println("InsertionSort(): ");
        testSort(new InsertionSort());
        System.out.println("BubbleSort(): ");
        testSort(new BubbleSort());
        System.out.println("MergeSort(): ");
        testSort(new MergeSort());
        System.out.println("QuickSort(): ");
        testSort(new QuickSort());
    }
    public static void testSort(Sort sorter) {
        Random random = new Random();
        int capacity = 10;
        int[] arr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = random.nextInt(capacity);
        }
        System.out.println(Arrays.toString(arr));
        sorter.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
/* EOF */