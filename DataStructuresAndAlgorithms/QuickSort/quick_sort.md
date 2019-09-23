# 快速排序 - Java 实现

算法思路：

1. 挑选基准值：从待排序列中挑出一个元素，作为「基准值（pivot）」；

2. 分割重排序：重新排序数列，所有比基准值小的元素摆放在基准值的前方，所有比基准值大的元素摆在基准值的后方（与基准值相等的数可以放到任何一边）。结束之后，对基准值的排序就已经完成；

3. 递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。

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
        if (arr == null || arr.length < 2 ||
        /* 递归退出条件 */
            left >= right) {
            return;
        }
        /* 分割排序 */
        int index = partition(arr, left, right);
        /* 排序左部分 */
        quickSort(arr, left, index - 1);
        /* 排序右部分 */
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

算法复杂度分析：

- 时间复杂度：最优情况`O(n*logn)`，最坏情况`O(n^2)`，平均情况`O(n*logn)`。

- 空间复杂度：最优情况`O(logn)`，最坏情况`O(n)`，平均情况`O(logn)`。

<!-- EOF -->
