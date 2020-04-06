#include <vector>

using namespace std;

/**
 * LeetCode 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int max = 0;
        int len = prices.size();
        for (int i = 0; i < len - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */