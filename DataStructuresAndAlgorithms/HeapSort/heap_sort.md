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
        if (arr == null || right - left + 1 < 2) {
            return;
        }
        for (int i = left; i <= right; ++i) {
            heapInsert(arr, i);
        }
        int heapSize = right - left + 1;
        do {
            swap(arr, left, heapSize - 1);
            heapSize--;
            heapify(arr, left, heapSize);
        } while (heapSize > 0);
    }
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 - 1;
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
                left = index * 2 - 1;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */
```
> 代码清单：堆排序 - Java 实现


<!-- EOF -->
