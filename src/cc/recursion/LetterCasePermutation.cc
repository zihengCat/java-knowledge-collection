#include <iostream>
#include <vector>
#include <string>

#include "../VectorHelper.h"

using namespace std;

/**
 * LeetCode 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 */
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        vector<string> resultVector = vector<string>();
        letterCasePermutation(s, 0, s.size(), "", resultVector);
        return resultVector;
    }
private:
    void letterCasePermutation(
        string originalString,
        int currentIndex,
        int maximumLength,
        string currentString,
        vector<string> &resultVector) {
        if (currentIndex >= maximumLength) {
            resultVector.push_back(currentString);
            return;
        }
        char c = originalString[currentIndex];
        if (isDigits(c)) {
            letterCasePermutation(
                originalString,
                currentIndex + 1,
                maximumLength,
                currentString + c,
                resultVector
            );
        } else {
            letterCasePermutation(
                originalString,
                currentIndex + 1,
                maximumLength,
                currentString + toUpper(c),
                resultVector
            );
            letterCasePermutation(
                originalString,
                currentIndex + 1,
                maximumLength,
                currentString + toLower(c),
                resultVector
            );
        }
    }
    bool isDigits(char c) {
        return c >= '0' && c <= '9';
    }
    char toLower(char c) {
        if ((c >= 'A') && (c <= 'Z')) {
            return c + ('a' - 'A');
        }
        return c;
    }
    char toUpper(char c) {
        if ((c >= 'a') && (c <= 'z')) {
            return c - ('a' - 'A');
        }
        return c;
    }
};

int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    string str = "a1b2";
    vector<string> resultVector = obj.letterCasePermutation(str);
    cout << vectorToString(resultVector) << endl;
    return 0;
}
/* EOF */