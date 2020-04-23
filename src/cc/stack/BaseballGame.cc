#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

/**
 * LeetCode 682. Baseball Game
 * https://leetcode.com/problems/baseball-game/
 */
class Solution {
public:
    int calPoints(vector<string>& ops) {
        stack<int> aStack = stack<int>();
        for (int i = 0; i < ops.size(); i++) {
            string op = ops[i];
            if (op == "+") {
                int pointA = aStack.top();
                aStack.pop();
                int pointB = aStack.top();
                aStack.pop();
                aStack.push(pointB);
                aStack.push(pointA);
                aStack.push(pointA + pointB);
            } else if (op == "D") {
                aStack.push(aStack.top() * 2);
            } else if (op == "C") {
                aStack.pop();
            } else {
                aStack.push(stoi(op));
            }
        }
        int sumValue = 0;
        while (!aStack.empty()) {
            sumValue += aStack.top();
            aStack.pop();
        }
        return sumValue;
    }
};

vector<string>* arrayToVector(string *arr, int size);
vector<string>* arrayToVector(string *arr, int size) {
    vector<string> *vec = new vector<string>();
    for (int i = 0; i < size; i++) {
        vec -> push_back(arr[i]);
    }
    return vec;
}

int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    string arr[] = {
        "5", "2", "C", "D", "+",
    };
    vector<string> *vec = arrayToVector(
        arr,
        sizeof(arr) / sizeof(arr[0])
    );
    cout << obj.calPoints(*vec) << endl;
    delete vec;
    return 0;
}
/* EOF */