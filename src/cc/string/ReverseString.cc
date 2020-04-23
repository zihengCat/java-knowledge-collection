#include <vector>

using namespace std;

/**
 * LeetCode 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 */
class Solution {
public:
    void reverseString(vector<char> &s) {
        // reverseStringArrayMerge(s);
        reverseStringTwoPointer(s);
    }
private:
    void reverseStringArrayMerge(vector<char> &str) {
        vector<char> reversedStr = vector<char>(str.size());
        for (int pStart = 0, pEnd = str.size() - 1;
            pEnd >= 0;
            pStart++, pEnd--) {
            reversedStr[pStart] = str[pEnd];
        }
        for (int i = 0; i < str.size(); i++) {
            str[i] = reversedStr[i];
        }
    }
    void reverseStringTwoPointer(vector<char> &str) {
        for (int pLeft = 0, pRight = str.size() - 1;
            pLeft < pRight; pLeft++, pRight--) {
            this -> swap(str, pLeft, pRight);
        }
    }
    void swap(vector<char> &vec, int i, int j) {
        int temp = vec[i];
        vec[i] = vec[j];
        vec[j] = temp;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */