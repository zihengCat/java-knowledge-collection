#include <vector>
#include <map>
#include <iostream>
#include "../VectorHelper.h"

using namespace std;

class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int n = nums.size();
        map<int, int> aMap;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (aMap.find(num) == aMap.end()) {
                aMap[num] = 1;
            } else {
                aMap[num] += 1;
            }
        }
        int half = n / 2;
        for (map<int, int>::iterator iter = aMap.begin();
            iter != aMap.end();
            iter++) {
            if (iter -> second > half) {
                return iter -> first;
            }
        }
        return -1;
    }
};


int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    vector<int> aVector;
    int arr[] = {3, 2, 3, 3, 1, 0, 3, };
    addElements(aVector, arr, sizeof(arr) / sizeof(arr[0]));
    cout << obj.majorityElement(aVector) << endl;
    return 0;
}
/* EOF */