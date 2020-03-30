#include <vector>
#include <algorithm>
using namespace std;
/**
 * LeetCode 977. Squares of a Sorted Array
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 */
class Solution {
public:
    vector<int> sortedSquares(vector<int>& A) {
        vector<int> resultVector = vector<int>(A.size());
        for (int i = 0; i < A.size(); i++) {
            resultVector[i] = A[i] * A[i];
        }
        sort(resultVector.begin(), resultVector.end());
        return resultVector;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */