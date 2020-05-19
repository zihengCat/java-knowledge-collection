#include <iostream>

class A {
private:
    int a2;
    int a1;
public:
    A(): a1(0), a2(a1 + 2) {
    }
    void print() {
        std::cout << "a1 = " << a1
            << " a2 = " << a2
            << std::endl;
    }
};

int main(int argc, char const *argv[]) {
    A a;
    a.print();
    return 0;
}
/* EOF */