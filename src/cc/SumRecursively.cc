#include <stdio.h>

#define N 10

int sumRecursively(int* arr, int length);
int sumRecursively0(int* arr, int index, int length);

int main(int argc, char const *argv[]) {
    int arr[N] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, };
    printf("%d\n", sumRecursively(arr, sizeof(arr) / sizeof(*arr)));
    return 0;
}

int sumRecursively(int* arr, int length) {
    if (arr == NULL) {
        return 0;
    }
    return sumRecursively0(arr, 0, length);
}

int sumRecursively0(int* arr, int index, int length) {
    if (index >= length) {
        return 0;
    }
    return arr[index] + sumRecursively0(arr, index + 1, length);
}
/* EOF */