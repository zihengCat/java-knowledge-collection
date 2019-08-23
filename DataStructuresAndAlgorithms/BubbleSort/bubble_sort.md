# 冒泡排序 - Java 实现

```java
import java.util.Arrays;
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        bubbleSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr, int left, int right) {
        for (int i = left; i <= right; ++i) {
            for (int j = left; j <= right; ++j) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }
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
