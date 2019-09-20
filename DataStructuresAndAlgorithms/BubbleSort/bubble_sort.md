# 冒泡排序 - Java 实现

算法思路：

1. 设数组`int[] arr`，长度为`n`

2. 比较数组元素`arr[0]`与`arr[1]`，如果`arr[0]`小于或大于`arr[1]`，交换元素

3. 类推：比较`arr[1]`与`arr[2]`，根据情况交换元素... 直到数组尾部，此时数组中最大（或最小）的一个数已经浮到数组尾部

4. 缩小循环范围`[0, n - 1]`，上重复上述流程，直至范围为`0`

```java
import java.util.Arrays;
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        bubbleSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr, int left, int right) {
        /**
         * 限定排序范围：
         * 0: [left, right - 0]
         * 1: [left, right - 1]
         * 2: [left, right - 2]
         * ...
         * n: [left, right - n]
         */
        for (int end = right; end > left; --end) {
            /**
             * 对比数组相邻元素，根据情况交换
             */
            for (int i = left; i < end; ++i) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }
    /* 数组元素交换函数 */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */
```
> 代码清单：冒泡排序 - Java 实现

<!-- EOF -->
