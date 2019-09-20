# 选择排序 - Java 实现

算法思路：

1. 从数组起始位置开始迭代遍历数组；

2. 对于每一轮迭代：寻找本轮迭代中数组最大（最小）一个数，将该数放置到本轮迭代数组头部；

3. 迭代遍历直至数组尾部。

```java
import java.util.Arrays;
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        selectionSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void selectionSort(int[] arr, int left, int right) {
        /* 异常检查 */
        if (arr == null || arr.length < 2) {
            return;
        }
        /* 遍历数组 */
        for (int i = left; i <= right; ++i) {
            /* 临时变量：纪录最大（最小）元素索引下标 */
            int index = i;
            /* 寻找本轮迭代数组中最大（最小）元素
               纪录其索引下标 */
            for (int j = i; j <= right; ++j) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            /* 将本轮迭代中数组最大（最小）元素放置到数组头部 */
            swap(arr, i, index);
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
> 代码清单：选择排序 - Java 实现

算法复杂度分析：

- 时间复杂度：`O(n^2)`

- 空间复杂度：`O(1)`

<!-- EOF -->
