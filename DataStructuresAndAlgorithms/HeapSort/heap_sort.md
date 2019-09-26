# 堆排序 - Java 实现

算法思路：

1. 将输入数组转化为一个堆（大顶堆/小顶堆）；

2. 交换堆顶与数组最后一个数（已排序一个数）；

3. 将堆大小减`1`，在`[0, N - 1]`位置上将数组重新调整为堆结构；

4. 重复上述过程，直至堆大小减为`0`。

```java
import java.util.Arrays;
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        heapSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void heapSort(int[] arr, int left, int right) {
        /* 异常情况 */
        if (arr == null || right - left + 1 < 2) {
            return;
        }
        /* 构建堆结构 */
        for (int i = left; i <= right; ++i) {
            heapInsert(arr, i);
        }
        /* 堆大小 */
        int heapSize = right - left + 1;
        do {
            /* 交换堆顶与未排序数组最后一个数 */
            swap(arr, left, heapSize - 1);
            /* 堆大小减 1 */
            heapSize--;
            /* 重新调整堆结构 */
            heapify(arr, left, heapSize);
        } while (heapSize > 0);
    }
    /**
     * 比较当前节点与其父节点，交换上浮，直至位置合适。
     * 算法复杂度：O(logN)
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    /**
     * 将数组重新调整为堆结构。
     *
     * 1. 取得当前节点左子节点；
     * 2. 比较当前节点、左子节点、右子节点，取最大（小）节点；
     * 3. 如果当前节点已为最大（小）节点，无需任何操作，直接退出；
     * 4. 交换当前节点与最大（小）节点，重复上述步骤。
     *
     * 算法复杂度：O(logN)
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left;
            if (left + 1 < heapSize && arr[left + 1] > arr[left]) {
                largest = left + 1;
            }
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            } else {
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }
    }
    /* 数组元素交换函数 */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */
```
> 代码清单：堆排序 - Java 实现

算法复杂度分析：

- 时间复杂度：`O(n*logn)`

- 空间复杂度：`O(1)`

<!-- EOF -->
