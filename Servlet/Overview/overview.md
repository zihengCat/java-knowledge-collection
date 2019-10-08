# Servlet 简介

Servlet（Server Applet），全称 Java Servlet，是用 Java 编写的服务器端程序。其主要功能在于交互式地浏览和修改数据，生成动态 Web 内容。狭义的 Servlet 是指 Java 语言实现的一个接口，广义的 Servlet 是指任何实现了这个 Servlet 接口的类，一般情况下，将 Servlet 理解为后者。

Servlet 运行于支持 Java 的应用服务器上。从实现上讲，Servlet 可以响应任何类型的请求，但绝大多数情况下 Servlet 只用来扩展基于 HTTP 协议的 Web 服务器。

# Java Web 目录结构

```plain
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── mygroup
    │   │       ├── controller
    │   │       │   ├── HomeController.java
    │   │       │   └── PersonController.java
    │   │       ├── dao
    │   │       │   └── PersonDao.java
    │   │       └── model
    │   │           └── Person.java
    │   ├── resources
    │   │   ├── db.properties
    │   │   ├── log4j.xml
    │   │   └── META-INF
    │   │       └── persistence.xml
    │   └── webapp
    │       ├── index.html
    │       ├── META-INF
    │       │   ├── context.xml
    │       │   └── MANIFEST.MF
    │       ├── resources
    │       │   └── css
    │       │       └── screen.css
    │       └── WEB-INF
    │           ├── spring
    │           │   ├── app
    │           │   │   ├── controllers.xml
    │           │   │   └── servlet-context.xml
    │           │   ├── db.xml
    │           │   └── root-context.xml
    │           ├── views
    │           │   ├── edit.jsp
    │           │   ├── home.jsp
    │           │   └── list.jsp
    │           └── web.xml
    └── test
        ├── java
        │   └── mygroup
        │       ├── controller
        │       │   ├── DataInitializer.java
        │       │   ├── HomeControllerTest.java
        │       │   └── PersonControllerTest.java
        │       └── dao
        │           └── PersonDaoTest.java
        └── resources
            ├── db.properties
            ├── log4j.xml
            ├── test-context.xml
            └── test-db.xml
```
> 注：Java Web 推荐目录结构

# 技术名词解释

- `POJO`：即 Plain Ordinary Java Object，简单老式 Java 对象。可以包含业务逻辑或持久化逻辑，但不担当任何特殊角色且不继承或不实现任何其它 Java 框架的类或接口。

- `DAO`：即 Data Access Object，数据访问对象。用于封装对数据库的增删改查（CRUD）操作：创建（Create）、更新（Update）、读取（Retrieve）、删除（Delete）。

- `DTO`：即 Data Transfer Object，数据传输对象。用于在跨进程传输或远程传输时使用的 Java 对象，不应包含业务逻辑，例如：一张数据表中包含`100`个字段，那么该数据表对应的`PO`对象就有`100`个属性（大多数情况下，DTO 对象数据会来自多张表）。但表示层（View）只需显示`10`个字段即可，那么就没有必要把整个`PO`对象传递到客户端，可以使用只有`10`个属性的`DTO`对象传输数据到客户端，这样也不会暴露服务端表结构，又可以有效节省传输数据量。当数据到达客户端后，如果用这个对象来对应界面显示，那此时它的身份就转换为`VO`。

- `PO`：即 Persistant Object，持久化对象。可以看成是与数据库中的表相映射的 Java 对象，最简单的 PO 就是对应数据库中某张表的一条数据记录，多条记录可以用 PO 集合表示。PO 应该不包含任何对数据库的操作，对象生命周期和数据库密切相关：向数据库插入记录时创建对象，删除或关闭数据库时对象消亡。

- `VO`：即 View Object，表示层对象。主要对应页面显示（Web 前端）的数据对象，通常用于业务层之间的数据传递，和`PO`一样也是仅包含数据，但应是抽象出的业务对象，可以和数据表对应，也可以不对应，根据具体业务需要而定。

- `BO`：即 Business Object，业务对象。封装了具体业务逻辑的 Java 对象，通过调用`DAO`方法，结合`PO`、`VO`进行实际业务操作。

- `IoC`：即 Inverse of Control，控制反转。并非具体技术，而是一种设计思想，将原本在程序中手动创建对象的控制权，交由框架来管理。

<!-- EOF -->
