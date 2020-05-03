#include <map>
#include <string>

using namespace std;

/**
 * LeetCode 383. Ransom Note
 * https://leetcode.com/problems/ransom-note/
 */
class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        map<char, int> magazineMap = buildMap(magazine);
        for (int i = 0; i < ransomNote.size(); i++) {
            char k = ransomNote[i];
            if (magazineMap.find(k) != magazineMap.end()
                && magazineMap[k] != 0) {
                magazineMap[k] -= 1;
            } else {
                return false;
            }
        }
        return true;
    }
private:
    map<char, int> buildMap(const string &str) {
        map<char, int> rMap = map<char, int>();
        for (int i = 0; i < str.size(); i++) {
            char k = str[i];
            if (rMap.find(k) == rMap.end()) {
                rMap[k] = 1;
            } else {
                rMap[k] += 1;
            }
        }
        return rMap;
    }
};

int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */