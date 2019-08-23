# 快速排序 - Java 实现

```java
import java.util.Arrays;
public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right || arr == null) {
            return;
        }
        int i = partition(arr, left, right);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    private static int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivot = arr[left];
        while (i < j) {
            while (arr[j] >= pivot && i < j) {
                --j;
            }
            while (arr[i] <= pivot && i < j) {
                ++i;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, i, left);
        return i;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */
```
> 代码清单：快速排序`Java`实现


