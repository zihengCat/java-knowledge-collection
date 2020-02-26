# 插入排序 - Java 实现



```java
import java.util.Arrays;
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        selectionSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertionSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = left + 1; i <= right; ++i) {
            for (int j = i; j > left; --j) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
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
> 代码清单：插入排序 - Java 实现



<!-- EOF -->
