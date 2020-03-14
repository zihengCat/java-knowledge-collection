# include <stdio.h>
# include <vector>
/**
 * LeetCode 343. Integer Break
 * https://leetcode.com/problems/integer-break/
 */
class IntegerBreak {
public:
    int integerBreak(int n) {
        if (n < 0) {
            return 0;
        }
        // 暴力递归
        // return integerBreakRecursively(n);

        // 记忆化搜索
        // memo = std::vector<int>(n + 1, -1);
        // return integerBreakTopToBottom(n);

        // 动态递推
        return integerBreakBottomToTop(n);
    }
private:
    /**
     * 动态递推
     *
     * @param n
     * @return int
     */
    int integerBreakBottomToTop(int n) {
        std::vector<int> dp = std::vector<int>(n + 1, -1);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = maxInt(
                    j * (i - j),
                    maxInt(dp[i], j * dp[i - j])
                );
            }
        }
        return dp[n];
    }
    std::vector<int> memo;
    /**
     * 记忆化搜索
     *
     * @param n
     * @return int
     */
    int integerBreakTopToBottom(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (memo[n] == -1) {
            int maxResult = -1;
            for (int i = 1; i < n; i++) {
                maxResult = maxInt(
                    i * (n - i),
                    maxInt(maxResult, i * integerBreakTopToBottom(n - i))
                );
            }
            memo[n] = maxResult;
        }
        return memo[n];
    }
    /**
     * 暴力递归
     *
     * @param n
     * @return int
     */
    int integerBreakRecursively(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int maxResult = -1;
        for (int i = 1; i < n; i++) {
            maxResult = maxInt(
                i * (n - i),
                maxInt(maxResult, i * integerBreakRecursively(n - i))
            );
        }
        return maxResult;
    }
    int maxInt(int a, int b) {
        return a > b ? a : b;
    }
};

int main(int argc, char const *argv[]) {
    IntegerBreak ib = IntegerBreak();
    int n = 10;
    printf("%d\n", ib.integerBreak(n));
    return 0;
}
/* EOF */