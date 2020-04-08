#ifndef __VECTOR_HELPER_H__
#define __VECTOR_HELPER_H__

#include <vector>
#include <string>

/**
 * 批量添加元素至 Vector 容器中
 *
 * @param vec
 * @param arr
 * @param size
 * @return void
 */
void addElements(std::vector<int>& vec, int arr[], int size);

// 使用模版
// template<typename T>
// void addElements(std::vector<T>& vec, T arr[], int size);

/**
 * Vector 字符串化。
 *
 * @param vec
 * @return string
 */
std::string vectorToString(std::vector<int>& vec);

void print(void);

#endif // __VECTOR_HELPER_H__
/* EOF */