- [1. @SpringBootApplication](#1-springbootapplication)
- [2. @RestController 配置返回json和xml](#2-restcontroller-配置返回json和xml)





#### 1. @SpringBootApplication

在SpringBoot中其中类中少不了@SpringBootApplication注解，这个注解相当于是几个注解的一个复合注解：@Configuration、@EnableAutoConfiguration、@ComponentScan

- @ComponentScan：如果不设置basePackage的话，默认会扫描包的所有类，所以最好还是写上basePackage，减少加载时间。默认扫描`**/*.class`路径，扫描该注解所在包下的所有类还有子包的所有类
- @Configuration：表示这个类是一个Spring配置类，一般这里面会定义Bean，会把这个类中的Bean加载到Spring容器中
- @EnableAutoConfiguration：该注解中最重要的是 @Import(AutoConfigurationImportSelector.class)注解。借助AutoConfigurationImportSelector ，@EnableAutoConfiguration 帮助Spring Boot 应用会将所有符合条件的@Configuration配置加载到当前的IOC容器中；会在开启某些功能的时候自动配置，这个注解告诉SpringBoot根据添加的jar依赖猜测我们想如何配置Spring

#### 2. @RestController 配置返回json和xml

参考文章：[Spring Boot中如何扩展XML请求和响应的支持](http://blog.didispace.com/spring-boot-xml-httpmessageconverter/)

​					[MappingJackson2HttpMessageConverter转换器](https://blog.csdn.net/qq_38921377/article/details/72910959)

​					[Jackson用于Bean和XML之间转换](https://blog.csdn.net/u014746965/article/details/78647616)

​					[自定义Jackson ObjectMapper](https://www.kancloud.cn/ahutchen/spring-boot-reference-guide/333370)

SpringBoot中处理HTTP请求的实现是采用SpringMVC，其中有个消息转换器的东西，主要负责处理各种不同格式的请求数据进行处理，并包转换成对象。

传统的SpringMVC需要配置xml文件，如下配置：

```xml

<!-- 设置json转换消息转换器，并且设置supportedMediaTypes  否则抛出406 -->
<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
    <property name="supportedMediaTypes">
        <list>
            <!-- 设置响应支持的类型 -->
            <value>text/html;charset=UTF-8</value>
            <!-- 设置请求body支持的类型 -->
            <value>application/x-www-form-urlencoded</value>
            <value>application/json;charset=UTF-8</value>
        </list>
    </property>
</bean>
```

SpringMVC中定义了`HttpMessageConverter`接口，抽象了消息转换器对类型的判断、对读写的判断与操作，如下定义：

```java
public interface HttpMessageConverter<T> {
    boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);

    boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);

    List<MediaType> getSupportedMediaTypes();

    T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException;

    void write(T t, @Nullable MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException;

}
```

如果要支持xml格式的消息转换，就必须要使用对应的转换器。SpringMVC中默认有一套采用Jackson实现的转换器`MappingJackson2XmlHttpMessageConverter，方法如下所示：

```java
@Configuration
public class MessageConverterConfig extends WebMvcConfigurationAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
        builder.indentOutput(true);
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
    }
}
```

SpringBoot中则不用配置，只需要引入相对应的包依赖，引入后SpringBoot会自动引入`MappingJackson2XmlHttpMessageConverter`的实现，之后通过XxxxMapping注解的produces进行指定返回的格式类型，如下：





SpringBoot的Rest接口返回格式可以通过XxxxMapping注解的produces进行指定，如果项目需要同时既能满足json与xml的返回格式就需要手动指定，如下：

```java
@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
@GetMapping(value = "/xml",produces = MediaType.APPLICATION_XML_VALUE)
```

其中的APPLICATION_XML_VALUE与APPLICATION_JSON_VALUE表示使用xml或者json返回结果

下面以返回xml为例：

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

2. 定义xml对象

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "User")
public class User {
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "age")
    private Integer age;
}
```

其中：`@Data`、`@NoArgsConstructor`、`@AllArgsConstructor`是lombok简化代码的注解，主要用于生成get、set以及构造函数。`@JacksonXmlRootElement`、`@JacksonXmlProperty`注解是用来维护对象属性在xml中的对应关系。

生成的xml如下：

```xml
<User>
	<name>aaaa</name>
	<age>10</age>
</User>
```

XML转换主要由四个注解：

- @JacksonXmlElementWrapper：可用于指定List等集合类，外围标签名
- @JacksonXmlProperty：指定包装标签名，或者指定标签内部的属性名
- @JacksonXmlRootElement：指定生成xml根标签的名字
- @JacksonXmlText：指定当前这个值，没有xml标签包裹

3. 创建接受xml请求的接口

```java
@Controller
public class UserController {

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User create(@RequestBody User user) {
        user.setName("didispace.com : " + user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }
}
```

