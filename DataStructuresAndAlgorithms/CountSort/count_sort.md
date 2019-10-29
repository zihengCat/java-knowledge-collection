# 计数排序 - Java 实现

算法思路：

1. 找出待排序的数组中最大和最小的元素；

2. 建立一个计数桶数组，包含排序数组值域范围的的所有数（最小/最大）；

3. 统计数组中每个值为`i`的元素出现次数，存入计数桶数组的第`i`项；

4. 反向填充目标数组，遍历计数桶数组，根据计数桶数组重建排序数组。

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
        /* 计数桶数组长度（值域上限） */
        int limit = 100;
        /* 建立计数桶数组 */
        int[] bucket = new int[limit];
        /* 将待排序数组元素依次放入计数桶数组中 */
        for (int i = left; i <= right; ++i) {
            bucket[arr[i]]++;
        }
        /* 根据计数桶数组重建排序数组 */
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

算法复杂度分析：

- 时间复杂度：`O(2n)`

- 空间复杂度：`O(n)`

<!-- EOF -->
