# 快速排序 - Java 实现

```java
import java.util.Arrays;
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr, int left, int right) {
        /* 处理异常情况 */
        if (arr == null || arr.length < 2 || left >= right) {
            return;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }
    private static int partition(int[] arr, int left, int right) {
        /* 取基准点 */
        int pivot = arr[left];
        /* 左指针 */
        int pLeft = left;
        /* 右指针 */
        int pRight = right;
        /* 左右指针前后遍历，
           找到大于（小于）基准点的值，交换 */
        while (pLeft < pRight) {
            while (arr[pRight] >= pivot && pLeft < pRight) {
                pRight--;
            }
            while (arr[pLeft] <= pivot && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(arr, pLeft, pRight);
            }
        }
        /* 将基准值放置到合适位置 */
        swap(arr, pLeft, left);
        /* 返回基准值索引下标 */
        return pLeft;
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
> 代码清单：快速排序 - Java 实现

<!-- EOF -->
