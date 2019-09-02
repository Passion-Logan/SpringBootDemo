- [1. 对Spring的理解](#1-对spring的理解)
- [2. 什么是IOC和DI，以及之间的区别和关系](#2-什么是ioc和di以及之间的区别和关系)
- [3. AOP是怎么实现的](#3-aop是怎么实现的)

#### 1. 对Spring的理解

- 方便解耦，简化开发

通过Spring提供的IoC容器，可以将对象之间对的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合

```java
// Controller
@Autowired
private UserService userService;
```

- AOP编程的支持 

通过Spring提供的AOP功能，方便进行面向切面的编程，如性能检测、事务管理、日志记录等。

- 声明式事务的支持
- 方便集成各种优秀的框架
- 降低JavaEE API的使用难度

如对JDBC、JavaMail，远程调用等提供了简便的封装。

#### 2. 什么是IOC和DI，以及之间的区别和关系

#### 3. AOP是怎么实现的