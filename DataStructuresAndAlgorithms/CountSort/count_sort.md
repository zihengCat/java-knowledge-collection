# 计数排序 - Java 实现

...

```java
import java.util.Arrays;
public class CountSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        countSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 计数排序。
     *
     * @param arr 待排序数组
     * @param left 数组左边界
     * @param right 数组右边界
     * @return void
     */
    public static void countSort(int[] arr, int left, int right) {
        /* 桶数组长度 */
        int limit = 100;
        /* 建立计数桶数组 */
        int[] bucket = new int[limit];
        /* 将待排序数组元素依次放入计数桶数组中 */
        for (int i = left; i <= right; ++i) {
            bucket[arr[i]]++;
        }
        /* 根据计数桶数组重建排序后数组 */
        for (int i = 0, j = 0; i < limit; ++i) {
            if (bucket[i] != 0) {
                for (int k = 0; k < bucket[i]; ++k) {
                    arr[left + j] = i;
                    ++j;
                }
            }
        }
    }
}
/* EOF */
```
> 代码清单：计数排序 - `Java`实现

<!-- EOF -->
