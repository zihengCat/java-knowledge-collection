#include <deque>
#include <string>

using namespace std;

/**
 * LeetCode 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
class ValidParentheses {
public:
    bool isValid(string s) {
        deque<char> stack = deque<char>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.at(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push_back(c);
            } else {
                if (stack.empty()) {
                    return false;
                } else if (c == ')' && stack.back() != '(') {
                    return false;
                } else if (c == ']' && stack.back() != '[') {
                    return false;
                } else if (c == '}' && stack.back() != '{') {
                    return false;
                }
                stack.pop_back();
            }
        }
        return stack.empty();
    }
};

int main(int argc, char const *argv[]) {
    /* ... */
    return 0;
}
/* EOF */