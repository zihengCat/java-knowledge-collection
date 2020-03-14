#include <stdio.h>
#include <time.h>
#include <vector>

using namespace std;

class FibonacciSequence {
private:
    int n;
    vector<int> memo;
public:
    int fib(int n);
    int fibTopToBottom(int n);
    int fibBottomToTop(int n);
    FibonacciSequence(int n);
    ~FibonacciSequence();
};

FibonacciSequence::FibonacciSequence(int n)
: n(n), memo(vector<int>(n + 1, -1)) {
    // ...
}
FibonacciSequence::~FibonacciSequence() {
    // ...
}
/**
 * 斐波那契数列 -> 暴力递归
 */
int FibonacciSequence::fib(int n) {
    if (n == 0) {
        return 0;
    }
    if (n == 1) {
        return 1;
    }
    return fib(n - 1) + fib(n - 2);
}
/**
 * 斐波那契数列 -> 记忆化搜索
 */
int FibonacciSequence::fibTopToBottom(int n) {
    if (n == 0) {
        return 0;
    }
    if (n == 1) {
        return 1;
    }
    if (memo[n] == -1) {
        memo[n] = fibTopToBottom(n - 1) + fibTopToBottom(n - 2);
    }
    return memo[n];
}
/**
 * 斐波那契数列 -> 动态递推
 */
int FibonacciSequence::fibBottomToTop(int n) {
    vector<int> dp = vector<int>(n + 1);
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}

int main(int argc, char const *argv[]) {
    int n = 42;
    FibonacciSequence fib = FibonacciSequence(n);

    time_t startTime = clock();
    int result1 = fib.fib(n);
    int result2 = fib.fibTopToBottom(n);
    int result3 = fib.fibBottomToTop(n);
    time_t endTime = clock();

    printf("fib(%d): %d, cost: %ld\n",
        n, result1, endTime - startTime
    );
    printf("fibTopToBottom(%d): %d, cost: %ld\n",
        n, result2, endTime - startTime
    );
    printf("fibBottomToTop(%d): %d, cost: %ld\n",
        n, result3, endTime - startTime
    );

    return 0;
}
/* EOF */