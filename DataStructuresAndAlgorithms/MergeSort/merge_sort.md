# 归并排序 - Java 实现

算法思路：

1. 设有数组`int[] arr`，其长度为`n`；

2. 取中点分割原数组，分割为两个子数组：`[left, mid]`、`[mid + 1, right]`；

3. 递归分割直至数组不可再分割（长度为1，只剩下单个元素），执行归并操作（具体如下）。
    1. 申请辅助数组，长度为原数组长度；
    2. 左、右指针指向已排序序列左右起点；
    3. 比较大小，放入辅助数组中，指针后移，直至某一序列遍历完成；
    4. 将未完成遍历子序列的剩余元素拷贝到辅助数组中；
    5. 辅助数组排序完成，覆盖原数组。

```java
import java.util.Arrays;
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr, int left, int right) {
        /* 处理异常情况 */
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, left, right);
    }
    private static void sort(int[] arr, int left, int right) {
        /* 递归退出：分割数组长度为1 */
        if (left == right) {
            return;
        }
        /* 取得中点 */
        int mid = (left + right) / 2;
        /* 排序左部分 */
        sort(arr, left, mid);
        /* 排序右部分 */
        sort(arr, mid + 1, right);
        /* 归并 */
        merge(arr, left, mid, right);
    }
    /**
     * 以 mid 分界，左右数组都已排好序，
     * 归并外排使得数组整体有序。
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        /* 引入辅助数组 */
        int[] arrCopy = new int[right - left + 1];
        /* 迭代变量 */
        int i = 0;
        /* 左指针 */
        int pLeft = left;
        /* 右指针 */
        int pRight = mid + 1;
        /* 外排左右数组 */
        while (pLeft <= mid && pRight <= right) {
            if (arr[pLeft] < arr[pRight]) {
                arrCopy[i] = arr[pLeft];
                pLeft++;
            } else {
                arrCopy[i] = arr[pRight];
                pRight++;
            }
            i++;
        }
        /* 有且仅有一边数组遍历完成且越界，
           将另一边数组剩余元素拷贝到辅助数组中 */
        while (pLeft <= mid) {
            arrCopy[i] = arr[pLeft];
            pLeft++;
            i++;
        }
        while (pRight <= right) {
            arrCopy[i] = arr[pRight];
            pRight++;
            i++;
        }
        /* 辅助数组覆盖原数组 */
        for (int j = 0; j < arrCopy.length; ++j) {
            arr[left + j] = arrCopy[j];
        }
    }
}
/* EOF */
```
> 代码清单：归并排序 - Java 实现

算法复杂度分析：

- 时间复杂度：`O(n*logn)`

- 空间复杂度：`O(n)`

<!-- EOF -->
