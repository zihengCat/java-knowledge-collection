#include <vector>
#include <set>
#include <map>
#include <utility>

using namespace std;

/**
 * LeetCode 532. K-diff Pairs in an Array
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        // return findPairsBruteForce(nums, k);
        return findPairsHashTable(nums, k);
    }
private:
    /**
     * Brute Force
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param nums
     * @param k
     * @return int
     */
    int findPairsBruteForce(vector<int>& nums, int k) {
        set<pair<int, int>> aSet;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j
                    && nums[i] <= nums[j]
                    && nums[j] - nums[i] == k) {
                    aSet.insert(make_pair(nums[i], nums[j]));
                }
            }
        }
        return aSet.size();
    }
    /**
     * Hash Table
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums
     * @param k
     * @return int
     */
    int findPairsHashTable(vector<int>& nums, int k) {
        map<int, int> cnt;
        for (int n : nums) {
            if (cnt.find(n) != cnt.end()) {
                cnt.at(n) += 1;
            } else {
                cnt.insert(make_pair(n, 1));
            }
        }
        int r = 0;
        for(map<int, int>::iterator iter = cnt.begin();
            iter != cnt.end();
            iter++) {
            int x = iter -> first;
            if ((k > 0 && cnt.find(x + k) != cnt.end())
                || (k == 0 && cnt.find(x) -> second > 1)) {
                r++;
            }
        }
        return r;
    }
};

int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    vector<int> nums = {3, 1, 4, 1, 5, };
    int k = 2;
    printf("%d\n", obj.findPairs(nums, k));
    return 0;
}

/* EOF */