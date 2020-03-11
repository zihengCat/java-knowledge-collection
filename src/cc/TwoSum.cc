#include <vector>
#include <map>

using namespace std;

class TwoSum {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> resultList = vector<int>();
        map<int, int> aMap = map<int, int>();
        for (int i = 0; i < nums.size(); i++) {
            int complementNum = target - nums.at(i);
            if (aMap.find(complementNum) != aMap.end()) {
                resultList.push_back(i);
                resultList.push_back(aMap.find(complementNum) -> second);
                return resultList;
            }
            aMap.insert(pair<int, int>(nums.at(i), i));
        }
        return resultList;
    }
};

int main(int argc, char const *argv[]) {
    /* code */
    return 0;
}
/* EOF */