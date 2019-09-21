# 归并排序 - Java 实现


```java
import java.util.Arrays;
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 6, 8, 9, 5, 0, 4, 7};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr, int left, int right) {
        /* 处理异常 */
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
        int mid = (left + right) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        /* 使用辅助数组 */
        int[] arrCopy = new int[right - left + 1];
        /* 迭代变量，临时指针 */
        int i = 0, pLeft = left, pRight = mid + 1;
        while (pLeft <= mid && pRight <= right) {
            if (arr[pLeft] < arr[pRight]) {
                arrCopy[i] = arr[pLeft];
                pLeft++;
            } else {
                arrCopy[i] = arr[pRight];
                pLeft++;
            }
            i++;
        }
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
        /* 覆盖原数组 */
        for (i = 0; i < arrCopy.length; ++i) {
            arr[left + i] = arrCopy[i];
        }
    }
}
/* EOF */
```
> 代码清单：归并排序 - Java 实现


<!-- EOF -->
