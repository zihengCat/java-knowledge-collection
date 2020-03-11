#include <stdio.h>

#define N 10

int binarySearch(int* arr, int target, int left, int right);

int main(int argc, char const *argv[]) {
    int arr[N] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, };
    printf("%d\n", binarySearch(arr, 7, 0, sizeof(arr) / sizeof(*arr) - 1));
    return 0;
}

int binarySearch(int* arr, int target, int left, int right) {
    while (left < right) {
        int mid = (left + right) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            right = mid - 1;
        } else if (arr[mid] < target) {
            left = mid + 1;
        }
    }
    return -1;
}
/* EOF */