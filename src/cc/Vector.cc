#include <vector>
#include <cstdio>

using namespace std;

int main(int argc, char const *argv[]) {
    vector<int> aList = vector<int>();
    int capacity = 10;
    for (int i = 0; i < capacity; i++) {
        aList.push_back(i);
    }
    printf("%d\n", aList.size() == capacity);
    return 0;
}
/* EOF */