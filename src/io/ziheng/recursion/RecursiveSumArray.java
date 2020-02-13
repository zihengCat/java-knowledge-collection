package io.ziheng.recursion;
public class RecursiveSumArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 10, };
        System.out.println(sum(arr));
    }
    /**
     * Sum(arr[0, 1, 2, 3, ..., N-1]) =
     * arr[0] + Sum(arr[1, 2, 3, ..., N-1])
     * arr[0] + (arr[1] + Sum(arr[2, 3, 4, ..., N-1]))
     * ...
     * ... + arr[N-1] + Sum(arr[N])->0
     */
    public static int sum(int[] arr) {
        return sumRecursively(arr, 0, arr.length);
    }
    private static int sumRecursively(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int leftNum = arr[left];
        int sumNum = leftNum + sumRecursively(arr, left + 1, right);
        return sumNum;
    }
}
/* EOF */