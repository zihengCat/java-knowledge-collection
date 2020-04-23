#include <iostream>
#include <vector>

#include "../VectorHelper.h"

using namespace std;

/**
 * LeetCode 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
class Solution {
public:
    int search(vector<int> &nums, int target) {
        // return searchBruteForce(nums, target);
        return searchBinarySearch(nums, target);
    }
private:
    int searchBruteForce(vector<int> &nums, int target) {
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
    int searchBinarySearch(vector<int> &nums, int target) {
        if (nums.size() == 0) {
            return -1;
        }
        int breakingPoint = findBreakingPoint(nums);
        int lowerBound = 0;
        int upperBound = nums.size() - 1;
        int findLeftPart = binarySearch(
            nums,
            lowerBound,
            breakingPoint - 1,
            target
        );
        int findRightPart = binarySearch(
            nums,
            breakingPoint,
            upperBound,
            target
        );
        return findLeftPart != -1 ? findLeftPart
            : (findRightPart != -1 ? findRightPart : -1);
    }
    int findBreakingPoint(vector<int> &nums) {
        int breakingPoint = 0;
        int size = nums.size();
        for (int i = 1; i < size; i++) {
            if (nums[i] < nums[i - 1]) {
                return i;
            }
        }
        return breakingPoint;
    }
    int binarySearch(vector<int> &nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        // ERROR ->
        // int mid = (left + right) / 2;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, left, mid - 1, target);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, right, target);
        }
        return -1;
    }
};

int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    int nums[] = {4, 5, 6, 7, 0, 1, 2, };
    vector<int> vec = vector<int>();
    addElements(vec, nums, sizeof(nums)/sizeof(nums[0]));
    int target = 0;
    cout << obj.search(vec, target) << endl;
    target = 3;
    cout << obj.search(vec, target) << endl;
    return 0;
}
/* EOF */