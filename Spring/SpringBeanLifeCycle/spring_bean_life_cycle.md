# Spring Bean 生命周期

...

# Spring Bean 实例化过程

实例化过程包含初始化过程。


# Spring 上下文环境

- Spring Bean 定义：`BeanDefinition`

`BeanDefinition` 是 Spring 对 Bean 的定义描述。

| 方法  | 意义  |
| :--- | :--- |
| `setBeanClassName()`     | Bean 名称 |
| `setBeanClass()`         | Bean 类型 |
| `setScope()`             | Bean 作用域 |
| `setLazyInit()`          | Bean 延迟加载 |
| `setDependsOn()`         | Bean 依赖加载 |
| `setDescription()`       | Bean 描述信息 |
| ...                      | ... |
| `setFactoryBeanName()`   | Bean 工厂名称 |
| `setFactoryMethodName()` | Bean 工厂方法 |
| `isSingleton()`          | 是否单例 |
| `isPrototype()`          | 是否原型 |
| `isAbstract()`           | 是否抽象 |


# Spring Bean 工厂 `BeanFactory`

Spring 上下文环境核心 Bean 工厂，由多枚组件组成，也被称为 Spring 容器。

- 核心存储组件`beanDefinitionMap`：用于存放 Bean 定义，类型为`Map<String, BeanDefinition>`，选用并发哈希表`ConcurrentHashMap`作为底层容器。

- 单例对象池`singletonObjects`：用于缓存已实例化的 Bean 对象，类型为`Map<String, Object>`，选用并发哈希表`ConcurrentHashMap`作为底层容器。

# ...

- Spring 上下文环境：`ApplicationContext`

- 使用 XML 构建 Spring 上下文环境：`ClassPathXmlApplicationContext`

- 使用 注解配置类 构建 Spring 上下文环境：`AnnotationConfigApplicationContext`




- `BeanPostProcessor`

实现该接口的类参与 Spring Bean 的**初始化**过程。

- `BeanFactoryPostProcessor`

Spring Bean Factory 后置处理器，执行 Spring 内部一些对 Bean Factory 的操作，也对外开放。

- `invokeBeanFactoryPostProcessors(beanFactory)`
    - 扫描指定包下的类
    - 处理`import`逻辑
    - 将配置类解析为`BeanDefinition`后装入`beanDefinitionMap`
    - 调用`BeanFactoryPostProcessor`后置处理器（系统内置、用户自定义）

- `finishBeanFactoryInitialization(beanFactory)`
    - 实例化 Bean 类

```plain
org.springframework.beans.factory.config.ConfigurableListableBeanFactory#preInstantiateSingletons
    org.springframework.beans.factory.support.AbstractBeanFactory#getBean
        org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton

```
- 具体实例化 Bean 单例类
- Step 1. 取出需要实例化的 Bean 名称，使用`List<String>`承接。
- Step 2. 从`beanDefinitionMap`中根据名称取得`BeanDefinition`。
- Step 3. 一系列条件判断（作用域、是否延迟加载、是否抽象...）。
- Step 4. 进入`getBean()`逻辑，判断 Bean 是否实例化
- Step 5. 处理循环依赖（如果存在）
- Step 6. 创建 Bean 对象实例`createBean()`方法：
    - 解析出类元信息`resolveClass()`




# `BeanFactory` 与 `FactoryBean`

...

## 循环依赖创建

```plain
A -> B
A <- B
```
> 注：循环依赖问题

- `isSingletonCurrentlyInCreation(String beanName)`

Bean 创建状态判断

- 中间状态：类对象已创建，但 Bean 整体流程未走完。

- 中间状态池`singletonsCurrentlyInCreation`：用于存放正处于创建状态的 Bean 名称，类型为`Set<String>`，选用`ConcurrentHashMap`作为底层线程安全容器。

- 单例对象池`singletonObjects`：用于存放已经创建完成的 Bean 。

- 创建 Bean 对象实例`createBean()`方法：



<!-- EOF -->