#include <vector>
#include <iostream>

using namespace std;

/**
 * LeetCode 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/
 */
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int pA = 0;
        int pB = 0;
        for (/* ... */; pA < nums.size(); pA++) {
            if (nums[pA] != 0) {
                swap(nums, pA, pB);
                pB++;
            }
        }
        for (/* ... */; pB < nums.size(); pB++) {
            nums[pB] = 0;
        }
    }
    void printVector(string header, vector<int>& vec) {
        cout << header;
        for (int i = 0; i < vec.size(); i++) {
            cout << vec[i] << ", ";
        }
        cout << endl;
    }
private:
    void swap(vector<int>& nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
};
int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    vector<int> nums = {
        0, 1, 0, 3, 12,
    };
    obj.printVector("before: ", nums);
    obj.moveZeroes(nums);
    obj.printVector("after: ", nums);
    return 0;
}
/* EOF */