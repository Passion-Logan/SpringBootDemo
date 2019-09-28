- [1、基本解释](#1-基本解释)
- [2、SpringBoot中使用Sqlite](#2-springboot中使用sqlite)





#### 1、基本解释

**SQLite**是遵守ACID的关联式数据库管理系统，它包含在一个相对小的C库中。它是D.RichardHipp建立的公有领域项目。

不像常见的客户-服务器范例，SQLite引擎不是个程序与之通信的独立进程，而是连接到程序中成为它的一个主要部分。所以主要的通信协议是在编程语言内的直接API调用。这在消耗总量、延迟时间和整体简单性上有积极的作用。整个数据库(定义、表、索引和数据本身)都在宿主主机上存储在一个单一的文件中。它的简单的设计是通过在开始一个事务的时候锁定整个数据文件而完成的。

- 特征：

  库实现了多数的SQL-92标准，包括事务，就是代表原子性、一致性、隔离性和持久性的（ACID），触发器和多数的复杂查询。不进行类型检查。你可以把字符串插入到整数列中。例如，某些用户发现这是使数据库更加有用的创新，特别是与无类型的脚本语言一起使用的时候。其他用户认为这是主要的缺点。

  多个进程或线程可以访问同一个数据而没有问题。可以并行的满足多个读访问。只有在其他访问当前不被服务的时候才能满足写访问；否则写访问失败并带有一个错误代码(也可以在可配置的超时过期之后自动的重试)。

  提供了叫做`sqlite`的一个独立程序用来查询和管理SQLite数据库文件。 它也充当写使用SQLite库的应用的一个例子。

- SQLite管理客户端

  SQLite亦可以作为桌面数据库使用，以下为第三方SQLite的GUI软件。例如，

  - SQLiteMan，使用QT开发的一个SQLite客户端，支持多语言、跨平台。SQLiteMan
  - SQLite Manager, 以 火狐浏览器的扩展形式提供的SQLite客户端。
  - SQLite Database Browser, a graphical client to access SQLite databases
  - SqlPro SQL Client, another graphical client to work with SQLite databases

注：在线文档：[SQLite在线文档](http://www.ostools.net/apidocs/apidoc?api=sqlite)

#### 2、SpringBoot中使用SQLite

- 创建SQLite数据库

  main下创建一个db文件夹，右键-->New-->Data Source-->选择数据库后-->Open Console，运行如下脚本，创建一个表：

  ```sql
  create table hello
  (
    id    INTEGER primary key,
    title varchar(150),
    text  TEXT
  );
  ```

- Maven引入依赖

  ```xml
  <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.21.0.1</version>
  </dependency>
  ```

- 指定配置文件：

  ```xml
  spring.datasource.driver-class-name=org.sqlite.JDBC
  spring.datasource.url=jdbc:sqlite:E:/Java/demo1/src/main/db/myDb
  spring.datasource.username=root
  spring.datasource.password=root
  ```

参考文章：[Spring Boot操作Sqlite数据库 从入门到跑路](https://segmentfault.com/a/1190000016176354?utm_source=tag-newest)

#### 3、列式存储

