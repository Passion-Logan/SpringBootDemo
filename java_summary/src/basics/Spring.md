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

区别：

- IOC控制反转：说的是创建对象实例的控制权从代码控制剥离到IOC容器控制，侧重与于原理。
- DI依赖注入：说的是创建对象实例时，为这个对象注入属性值或其它对象实例，侧重于实现。

`控制反转是一种思想，如何实现控制反转呢，注入是一种实现手段`

关系：

- DI不能单独存在，DI需要在IOC的基础上来完成

在Java中依赖注入有三种实现方式：

- 构造器注入
- Setter方法注入
- 接口注入

Spring中 BeanFactory接口是Spring IoC容器的核心接口。ApplicationContext接口对BeanFactory(是一个子接口)进行了扩展，我们常用的ClassPathXmlApplicationContexWeb.xml监听器来负责启动初始化Spring容器。

#### 3. AOP是怎么实现的

#### 4. Spring Bean的生命周期