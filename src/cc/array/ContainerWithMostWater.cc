#include <vector>
#include <cstdio>
#include <iostream>

#include "../VectorHelper.h"

using namespace std;

class Solution {
public:
    int maxArea(vector<int>& height) {
        // 暴力法
        // return maxAreaBruteForce(height);
        // 双指针
        return maxAreaTwoPointer(height);
    }
private:
    /**
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return int
     */
    int maxAreaTwoPointer(vector<int>& height) {
        int maxArea = 0;
        for (int pLeft = 0, pRight = height.size() - 1;
            pLeft < pRight; /* ... */) {
            int minHeight = 0;
            if (height[pLeft] < height[pRight]) {
                minHeight = height[pLeft];
                pLeft++;
            } else {
                minHeight = height[pRight];
                pRight--;
            }
            int newArea = (pRight - pLeft + 1) * minHeight;
            maxArea = max(maxArea, newArea);
        }
        return maxArea;
    }
    /**
     * 暴力枚举
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return int
     */
    int maxAreaBruteForce(vector<int>& height) {
        int maxArea = 0;
        int arrayLength = height.size();
        for (int i = 0; i < arrayLength; i++) {
            for (int j = i + 1; i < arrayLength; j++) {
                maxArea = max(
                    maxArea,
                    min(height[i], height[j])
                    * (j - i)
                );
            }
        }
        return maxArea;
    }
    int max(int a, int b) {
        return a > b ? a : b;
    }
    int min(int a, int b) {
        return a < b ? a : b;
    }
};
/**
 * 主函数 -> 测试用例
 */
int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    // C++ 98
    vector<int> height = vector<int>();
    int arr[] = {
        1, 8, 6, 2, 5, 4, 8, 3, 7,
    };
    addElements(height, arr, sizeof(arr) / sizeof(arr[0]));
    // C++ 11
    // vector<int> height = {
    //     1, 8, 6, 2, 5, 4, 8, 3, 7,
    // };
    printf(
        "vector.size(): %lu\nresult: %d\n",
        height.size(),
        obj.maxArea(height)
    );
    cout << vectorToString(height) << endl;
    return 0;
}
/* EOF */