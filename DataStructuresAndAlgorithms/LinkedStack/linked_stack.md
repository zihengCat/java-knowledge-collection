# 链表实现栈（Java）

栈（Stack）是一种后进先出（LIFO）的线性数据结构，具体实现上有：顺序栈、链式栈。

本文讲解在 Java 中使用链表实现链式栈。

# 实现思路

链表实现栈结构，底层数据结构选用双向链表，准备栈底变量`base`，栈顶指针`top`，引入一个`size`变量指示栈中已存放元素数量。

- 入栈：将元素包装为双向链表节点，尾插法插入到链表中，栈顶指针指向当前节点。

- 出栈：暂存栈顶指针所指节点元素，从链表中移除栈顶节点，栈顶指针指向其前驱节点。

# 链式栈测试代码（TDD）

秉持着测试驱动开发（TDD）原则，先编写链式栈测试代码。

```java
import Stack;
import StackIsFullException;
import StackIsEmptyException;
import LinkedStack;
public class LinkedStackTest {
    public static void main(String[] args) {
        int capacity = 128;
        Stack<Integer> linkedStack = new LinkedStack<Integer>();
        System.out.println("Before push elements, LinkedStack.size(): " +
            linkedStack.size()
        );
        for (int i = 0; i < capacity; ++i) {
            linkedStack.push(i);
        }
        System.out.println("After push elements, LinkedStack.size(): " +
            linkedStack.size()
        );
        System.out.println("LinkedStack.top(): " + linkedStack.top());
        while (!linkedStack.isEmpty()) {
            System.out.println(linkedStack.pop());
        }
        System.out.println("After pop elements, LinkedStack.size(): " +
            linkedStack.size()
        );
    }
}
/* EOF */
```
> 代码清单：链式栈测试代码（Java）





<!-- EOF -->
