#include <string>

using namespace std;

/**
 * LeetCode 1790. Check if One String Swap Can Make Strings Equal
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 */
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char s1Arr[2] = {};
        char s2Arr[2] = {};
        int idx = 0;
        int sLen = s1.length();
        for (int i = 0; i < sLen; i++) {
            if (s1[i] != s2[i]) {
                if (idx > 2) {
                    return false;
                }
                s1Arr[idx] = s1[i];
                s2Arr[idx] = s2[i];
                idx++;
            }
        }
        if ((s1Arr[0] != s2Arr[1]) || (s1Arr[1] != s2Arr[0])) {
            return false;
        }
        return true;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}

/* EOF */
