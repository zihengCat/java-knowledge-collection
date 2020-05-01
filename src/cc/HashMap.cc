#include <map>
#include <cstdio>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[]) {
    map<int, int> aMap = map<int, int>();
    for (int i = 0; i < 10; i++) {
        aMap.insert(pair<int, int>(i, i * 10));
    }
    for (map<int, int>::iterator iter = aMap.begin();
        iter != aMap.end();
        iter++) {
        std::cout << iter -> first << " -> "
                  << iter -> second << std::endl;
    }
    printf("%d", aMap.find(9) == aMap.end());
    return 0;
}
/* EOF */