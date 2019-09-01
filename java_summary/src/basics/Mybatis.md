- [1. 多表联合查询时遇到字段名重复，该如何配置避免报错](#1-多表联合查询时遇到字段名重复该如何配置避免报错)
- [2. MyBatis批量更新集合对象，需要实现什么配置？](#2-mybatis批量更新集合对象需要实现什么配置)
- [3. javaBean和jdbcType之间的切换需要如何配置](#3-javabean和jdbctype之间的切换需要如何配置)

#### 1. 多表联合查询时遇到字段名重复，该如何配置避免报错

解决这种相同字段名的问题，最简单的就是在配置resultMap映射的时候就`设置别名`，例如：

```xml
<resultMap id="BaseResultMap" type="com.hand.core.demos.dto.Demokey">
    <result column="id" property="id" jdbcType="DECIMAL" />
    <result column="demo_id" property="demoId" jdbcType="DECIMAL" />
    <result column="name" property="name" jdbcType="VARCHAR" />
</resultMap>
<!--联合查询 star-->
<resultMap id="WithDemoResultMap" type="com.hand.core.demos.dto.Demokey">
    <result column="id" property="id" jdbcType="DECIMAL" />
    <result column="demo_id" property="demoId" jdbcType="DECIMAL" />
    <result column="name" property="name" jdbcType="VARCHAR" />
    <association property="demos" javaType="com.hand.core.demos.dto.Demos">
        <result column="id" property="id" jdbcType="DECIMAL" />
        <result column="name" property="name" jdbcType="VARCHAR" />
    </association>
</resultMap>
<sql id="WithDemos_Column_List">
    k.id,k.demo_id,k.name,d.id,d.name
</sql>
<select id="selectWithDemos" parameterType="com.hand.core.demos.dto.Demokey" resultMap="WithDemoResultMap">
    SELECT <include refid="WithDemos_Column_List"/>
    from hap_demokey k LEFT JOIN hap_demos d on k.demo_id = d.id
</select>
<!--联合查询 end-->
```

按照本来的意思是：

k.id,k.demo_id,k.name,d.id,d.name 

这几个字段应该是映射

Demokey.id,Demokey.demo_id,Demokey.name,Demos.id,Demos.name

可实际上Mybatis 的映射是：

Demokey.id,Demokey.demo_id,Demokey.name,Demokey.id,Demokey.name

解决办法就是使用`字段的别名`，如下：

```xml
<!--以前的-->
<association property="demos" javaType="com.hand.core.demos.dto.Demos">
    <result column="id" property="id" jdbcType="DECIMAL" />
    <result column="name" property="name" jdbcType="VARCHAR" />
</association>
<sql id="WithDemos_Column_List">
    k.id,k.demo_id,k.name,d.id,d.name
</sql>
<!--替换后的-->
<association property="demos" javaType="com.hand.core.demos.dto.Demos">
    <result column="did" property="id" jdbcType="DECIMAL" />
    <result column="dname" property="name" jdbcType="VARCHAR" />
</association>
<sql id="WithDemos_Column_List">
    k.id,k.demo_id,k.name,d.id did,d.name dname
</sql>
```

#### 2. MyBatis批量更新集合对象，需要实现什么配置

参考文章：

[mybatis批量插入、批量更新和批量删除](https://www.jianshu.com/p/041bec8ae6d3)

[mybatis批量更新的一些问题](https://www.iteye.com/blog/lohasle-1740416)

使用mybatis批量操作时，都会使用到foreach标签，主要元素有`item，index，collection，open，separator，close`。

- item：表示集合中每一个元素进行迭代时的别名
- index：指定一个名字，用于表示在迭代过程中，每次迭代到的位置
- open：表示该语句以什么开始
- separator：表示在每次进行迭代之间以什么符号作为分隔符
- close：表示以什么结束
- collection：`该属性是必须指定的，在不同情况下的值不同`
  - 如果传入的是单参数且参数类型是一个List的时候collection属性值为list
  - 如果传入的是单参数且参数类型是一个Array的时候collection的属性值为array
  - 如果传入的参数的多个的时候，就需要封装成一个Map，这个时候collection的属性值就是传入的List或Array在自己封装的Map里面的Key

#### 3. javaBean和jdbcType之间的切换需要如何配置

配置直接通过mapper的配置文件，写resultMap即可

```xml
<resultMap id="BaseResultMap" type="com.demo.entity.Test" >
    <!--用id属性来映射主键字段-->
    <id column="TYPE_ID" property="typeId" jdbcType="INTEGER" />
    <!--用result属性来映射非主键字段，property为实体类字段名，column为数据表中的字段名-->
    <result column="TYPE" property="type" jdbcType="SMALLINT" />
    <result column="START_DATE" property="startDate" jdbcType="DATE" />
    <result column="END_DATE" property="endDate" jdbcType="DATE" />
</resultMap>
```