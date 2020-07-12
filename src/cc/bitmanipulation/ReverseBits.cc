#include <cstdint>
/**
 * LeetCode 190. Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 */ 
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t r = 0x00;
        for (int i = 0; i < 32; i++) {
            r <<= 1;
            r += n & 0x01;
            n >>= 1;
        }
        return r;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */ 