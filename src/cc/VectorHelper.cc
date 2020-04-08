#include <vector>
#include <string>
#include <sstream>

#include "VectorHelper.h"

void addElements(std::vector<int>& vec, int arr[], int size) {
    for (int i = 0; i < size; i++) {
        vec.push_back(arr[i]);
    }
}

std::string vectorToString(std::vector<int>& vec) {
    std::stringstream ss;
    ss << '[';
    for (int i = 0; i < vec.size(); i++) {
        ss << vec[i] << ", ";
    }
    ss << ']';
    return ss.str();
}

void print(void) {
    printf("hello");
}
/* EOF */