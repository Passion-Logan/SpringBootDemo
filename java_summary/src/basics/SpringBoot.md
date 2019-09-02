- [1. @SpringBootApplication](#1-springbootapplication)
- [2. @RestController 配置返回json和xml](#2-restcontroller-配置返回json和xml)
- [3. 编写启动命令来调用不同的开发环境和测试环境的配置文件](#3-编写启动命令来调用不同的开发环境和测试环境的配置文件)
- [4. SpringBoot读取配置的几种方式](#4-springboot读取配置的几种方式)





#### 1. @SpringBootApplication

在SpringBoot中其中类中少不了@SpringBootApplication注解，这个注解相当于是几个注解的一个复合注解：@Configuration、@EnableAutoConfiguration、@ComponentScan

- @ComponentScan：如果不设置basePackage的话，默认会扫描包的所有类，所以最好还是写上basePackage，减少加载时间。默认扫描`**/*.class`路径，扫描该注解所在包下的所有类还有子包的所有类
- @Configuration：表示这个类是一个Spring配置类，一般这里面会定义Bean，会把这个类中的Bean加载到Spring容器中
- @EnableAutoConfiguration：该注解中最重要的是 @Import(AutoConfigurationImportSelector.class)注解。借助AutoConfigurationImportSelector ，@EnableAutoConfiguration 帮助Spring Boot 应用会将所有符合条件的@Configuration配置加载到当前的IOC容器中；会在开启某些功能的时候自动配置，这个注解告诉SpringBoot根据添加的jar依赖猜测我们想如何配置Spring

#### 2. @RestController 配置返回json和xml

参考文章：

[Spring Boot中如何扩展XML请求和响应的支持](http://blog.didispace.com/spring-boot-xml-httpmessageconverter/)

[MappingJackson2HttpMessageConverter转换器](https://blog.csdn.net/qq_38921377/article/details/72910959)

[Jackson用于Bean和XML之间转换](https://blog.csdn.net/u014746965/article/details/78647616)

[自定义Jackson ObjectMapper](https://www.kancloud.cn/ahutchen/spring-boot-reference-guide/333370)

[SpringBoot 消息转换器 HttpMessageConverter](https://www.cnblogs.com/hongdada/p/9120899.html)

[SpringMVC produces属性含义](https://blog.csdn.net/lzwglory/article/details/17252099)

SpringBoot中处理HTTP请求的实现是采用SpringMVC，其中有个消息转换器的东西，主要负责处理各种不同格式的请求数据进行处理，并包转换成对象。

传统的SpringMVC需要配置xml文件，如下配置：

```xml
ia
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
        // 设置美化打印出
        builder.indentOutput(true);
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
    }
}
```

SpringBoot中则不用配置，只需要引入相对应的包依赖，引入后SpringBoot会自动引入`MappingJackson2XmlHttpMessageConverter`的实现，之后通过XxxxMapping注解的produces进行指定返回的格式类型，如下：

```java
@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
@GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
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

2. 定义xml关系对象

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

3. 创建接收xml请求的接口

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

#### 3. 编写启动命令来调用不同的开发环境和测试环境的配置文件

在SpringBoot中多环境配置文件名需要满足`applicaton-{profile}.properties/yml`，其中{profile}对应环境的标识：

- application-dev.properties/yml：开发环境
- application-prod.properties/yml：生产环境
- application-test.properties/yml：测试环境

至于具体哪个配置文件被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值。

例如：spring.profiles.active=dev，就会加载application-dev.properties配置文件的内容

通过不同的启动命令来调用不同的开发环境：

```java
// 执行开发环境，在application.properties中设置spring.profiles.active=dev，则默认是dev环境
// 执行测试环境的配置
java -jar xxx.jar --spring.profiles.active=test
// 执行生产环境的配置
java -jar xxx.jar --spring.profiles.active=prod
```

第二中是使用Maven Profile

pom.xml的配置如下：

```xml
<profiles>
    <!--开发环境-->
    <profile>
        <id>dev</id>
        <properties>
            <build.profile.id>dev</build.profile.id>
        </properties>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <!--测试环境-->
    <profile>
        <id>test</id>
        <properties>
            <build.profile.id>test</build.profile.id>
        </properties>
    </profile>
    <!--生产环境-->
    <profile>
        <id>prod</id>
        <properties>
            <build.profile.id>prod</build.profile.id>
        </properties>
    </profile>
</profiles>

<build>
    <finalName>${project.artifactId}</finalName>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources.${build.profile.id}</directory>
            <filtering>false</filtering>
        </resource>
    </resources>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <classifier>exec</classifier>
            </configuration>
        </plugin>
    </plugins>
</build>
```

通过执行`mvn clean package -P ${profile} `来指定使用哪个profile

#### 4. SpringBoot读取配置的几种方式

读取applicationo文件：

配置文件中有一个配置 info.address=TEST

- @Value注解读取方式

```java
@Value("${info.address}")
```

- @ConfigurationProperties注解读取方式

```java
// 在类上写注解
@Component
@ConfigurationProperties(prefix  = "info")
public class InfoConfig {
    private String address;
    
    public String getAddress() {
       return address;
   }
 
   public void setAddress(String address) {
       this.address = address;
   }
}
```

- 读取指定文件

在资源目录下建立config/db-config.propreties：

db.username=root

db.password=root

`@PropertySource+@Value注解方式读取`：@PropertySource注解不支持读取yml类型的文件

```java
@Component
@PropertySource(value = { "config/db-config.properties" })
public class DBConfig {
    @Value("${db.username}")
    private String username;
    
    @Value("${db.password}")
    private String password;
    
    ....getter setter方法
}
```

- Environment读取方式

以上所有加载出来的配置都可以通过Environment注入获取到

```java
@Autowired
private Environment env;

// 获取参数
String getProperty(String key);
```

