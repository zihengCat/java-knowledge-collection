class Solution {
public:
    int hammingDistance(int x, int y) {
        int z = x ^ y;
        int mask = 0x01;
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((z & mask) > 0) {
                cnt++;
            }
            z >>= 1;
        }
        return cnt;
    }
};
int main(int argc, char const *argv[]) {
    // ...
    return 0;
}
/* EOF */