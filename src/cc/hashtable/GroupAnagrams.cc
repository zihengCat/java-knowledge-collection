#include <vector>
#include <map>

using namespace std;

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        std::map<string, vector<string>> map =
            std::map<string, vector<string>>();
        for (int i = 0; i < strs.size(); i++) {
            string& s = strs[i];
            
            // sort(s.begin(), strs[i].end());
        }
    }

};
/* EOF */