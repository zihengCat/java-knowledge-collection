#include <utility>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[]) {
    pair<int, int> aPair = {1, 2, };
    pair<int, int*> bPair = make_pair(1, &aPair.first);
    pair<const char*, void*> cPair = make_pair(
        "hello",
        (void*)NULL
    );
    cout << aPair.first << endl;
    cout << aPair.second << endl;

    cout << cPair.first << endl;
    cout << cPair.second << endl;

    return 0;
}
/* EOF */