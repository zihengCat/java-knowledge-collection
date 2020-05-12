#include <array>
#include <utility>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[]) {
    pair<int, int> p = make_pair(1, 2);
    pair<int, double> pd = {1, 3.14};
    pair<int, int*> pc(0, NULL);
    array<int*, 3> arrPointer = {NULL, NULL, NULL, };
    array<int, 3> arr = {1, 2, 3};
    array<int, 3> arrInt = {1, 2, 3};
    // arr.fill(1024);
    cout << arr.size() << endl;
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << ' ';
    }
    cout << endl;
    for (int *iter = arr.begin(); iter != arr.end(); iter++) {
        cout << *iter << ' ';
    }
    cout << endl;
    for (int n : arr) {
        cout << n << ' ';
    }
    cout << endl;
    cout << "arr.size(): " << arr.size() << endl;
    cout << "arr.max_size(): " << arr.max_size() << endl;
    cout << "arr.front(): " << arr.front() << endl;
    cout << "arr.back(): " << arr.back() << endl;
    cout << "arr.data(): " << *(arr.data()) << endl;
    // cout << arr[40];
    return 0;
}
/* EOF */