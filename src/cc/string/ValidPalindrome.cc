#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * LeetCode 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 */
class Solution {
public:
    bool isPalindrome(string s) {
        // return isPalindromeCleanStringWay(s);
        return isPalindromeCharArrayWay(s);
    }
private:
    bool isPalindromeCharArrayWay(string s) {
        const char *sCharArray = s.c_str();
        for (int left = 0, right = s.size() - 1; left < right; left++, right--) {
            while (!isalnum(sCharArray[left]) && left < right) {
                left++;
            }
            while (!isalnum(sCharArray[right]) && left < right) {
                right--;
            }
            if (tolower(sCharArray[left]) != tolower(sCharArray[right])) {
                return false;
            }
        }
        return true;
    }
    bool isPalindromeCleanStringWay(string s) {
        vector<char> vec = cleanString(s);
        for (int left = 0, right = vec.size() - 1;
            left < right;
            left++, right--) {
            if (vec[left] != vec[right]) {
                return false;
            }
        }
        return true;
    }
    vector<char> cleanString(string s) {
        vector<char> resultVector = vector<char>();
        for (int i = 0; i < s.size(); i++) {
            if (isalpha(s[i]) || isdigit(s[i])) {
                resultVector.push_back(tolower(s[i]));
            }
        }
        return resultVector;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */