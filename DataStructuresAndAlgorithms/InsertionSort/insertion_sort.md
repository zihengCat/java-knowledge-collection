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
        for(int i = left; i <= right; ++i) {
            int val = arr[i];
            int j;
            for(j = i - 1; j >= 0 && arr[j] > val; --j) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = val;
        }
    }
}
/* EOF */
```
> 代码清单：插入排序 Java 实现

<!-- EOF -->
