#include <vector>
#include <set>

using namespace std;

/**
 * LeetCode ???. Counting Elements
 */
class Solution {
public:
    int countElements(vector<int>& arr) {
        int cnt = 0;
        set<int> aSet = set<int>();
        for (int i = 0; i < arr.size(); i++) {
            aSet.insert(arr[i]);
        }
        for (int i = 0; i < arr.size(); i++) {
            if (aSet.find(arr[i] + 1) != aSet.end()) {
                cnt++;
            }
        }
        return cnt;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */