- [1. @SpringBootApplication](#1-springbootapplication)





#### 1. @SpringBootApplication

在SpringBoot中其中类中少不了@SpringBootApplication注解，这个注解相当于是几个注解的一个复合注解：@Configuration、@EnableAutoConfiguration、@ComponentScan

- @ComponentScan：如果不设置basePackage的话，默认会扫描包的所有类，所以最好还是写上basePackage，减少加载时间。默认扫描`**/*.class`路径，扫描该注解所在包下的所有类还有子包的所有类
- @Configuration：表示这个类是一个Spring配置类，一般这里面会定义Bean，会把这个类中的Bean加载到Spring容器中
- @EnableAutoConfiguration：该注解中最重要的是 @Import(AutoConfigurationImportSelector.class)注解。借助AutoConfigurationImportSelector ，@EnableAutoConfiguration 帮助Spring Boot 应用会将所有符合条件的@Configuration配置加载到当前的IOC容器中；会在开启某些功能的时候自动配置，这个注解告诉SpringBoot根据添加的jar依赖猜测我们想如何配置Spring