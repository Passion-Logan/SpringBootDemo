- [1. @SpringBootApplication](#1-springbootapplication)





#### 1. @SpringBootApplication

在SpringBoot中其中类中少不了@SpringBootApplication注解，这个注解相当于是几个注解的一个复合注解：@Configuration、@EnableAutoConfiguration、@ComponentScan

- @ComponentScan：如果不设置basePackage的话，默认会扫描包的所有类，所以最好还是写上basePackage，减少加载时间。默认扫描`**/*.class`路径，扫描该注解所在包下的所有类还有子包的所有类
- @Configuration：表示这个类是一个Spring配置类，一般这里面会定义Bean，会把这个类中的Bean加载到Spring容器中
- @EnableAutoConfiguration：该注解中最重要的是 @Import(AutoConfigurationImportSelector.class)注解。借助AutoConfigurationImportSelector ，@EnableAutoConfiguration 帮助Spring Boot 应用会将所有符合条件的@Configuration配置加载到当前的IOC容器中；会在开启某些功能的时候自动配置，这个注解告诉SpringBoot根据添加的jar依赖猜测我们想如何配置Spring

#### 2. @RestController 配置返回json和xml

参考文章：[Spring Boot中如何扩展XML请求和响应的支持](http://blog.didispace.com/spring-boot-xml-httpmessageconverter/)

SpringBoot的Rest接口返回格式可以通过XxxxMapping注解的produces进行指定，如果项目需要同时既能满足json与xml的返回格式就需要手动指定，如下：

```java
@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
@GetMapping(value = "/xml",produces = MediaType.APPLICATION_XML_VALUE)
```

其中的APPLICATION_XML_VALUE与APPLICATION_JSON_VALUE表示使用xml或者json返回结果

SpringBoot中处理HTTP请求的实现是采用SpringMVC，其中有个消息转换器的东西，主要负责处理各种不同格式的请求数据进行处理，并包转换成对象。

SpringMVC中定义了`HttpMessageConverter`接口，抽象了消息转换器对类型的判断、对读写的判断与操作，如下定义：

```java

```



1. 引入包依赖：依赖包不齐全将发生406错误

```java
<!-- xml -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.6.0</version>
</dependency>
<!-- json -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.6.0</version>
</dependency>
```

2. 添加xml转换器

```java
@Configuration
public class MyWebConfiguration : WebMvcConfigurationAdapter {
    @Bean
    open fun jacksonXmlConverter() = MappingJackson2XmlHttpMessageConverter()
        
    override fun configurationMessageConverters(converters: List<HttpMessageConverter<*>>) {
        
    }
}
```

3. 测试例子，实体类自行创建

```java
@SpringBootApplication
@RestController
open class ProfileDemoApplication : CommandLineRunner {
    @Autowired
    private lateinit var repo: 
}
```

