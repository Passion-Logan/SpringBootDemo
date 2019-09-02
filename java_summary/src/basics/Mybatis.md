- [1. 多表联合查询时遇到字段名重复，该如何配置避免报错](#1-多表联合查询时遇到字段名重复该如何配置避免报错)
- [2. MyBatis批量更新集合对象，需要实现什么配置？](#2-mybatis批量更新集合对象需要实现什么配置)
- [3. javaBean和jdbcType之间的切换需要如何配置](#3-javabean和jdbctype之间的切换需要如何配置)
- [4. #{}和${}的区别是什么](#4-和的区别是什么)
- [5. Mybatis是如何进行分页的？分页插件的原理是什么？](#5-mybatis是如何进行分页的分页插件的原理是什么)

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

```xml
<update id="updateByBatch" parameterType="java.util.List">
    update t_goods
    set NODE_ID=
    <foreach collection="list" item="item" index="index"
             separator=" " open="case" close="end">
        when GOODS_ID=#{item.goodsId} then #{item.nodeId}
    </foreach>
    where GOODS_ID in
    <foreach collection="list" index="index" item="item"
             separator="," open="(" close=")">
        #{item.goodsId,jdbcType=BIGINT}
    </foreach>
</update>
```

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

#### 4. #{}和${}的区别是什么

`#{}`是预编译处理，`${}`是字符串替换

Mybatis在处理`#{}`时，会将sql中的`#{}`替换为？号，调用PreparedStatement的set方法来赋值；

Mybatis在处理`${}`时，就是把`${}`替换成变量的值。

使用`#{}`可以有效的放置SQL注入！

#### 5. Mybatis是如何进行分页的？分页插件的原理是什么？

Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页，可以在SQL内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。



分页插件基本是使用Mybatis提供的插件接口，实现自定义插件，在插件内的拦截方法内拦截待执行的SQL，然后重写SQL，根据dialect方言，添加对应的物理分页语句和物理分页参数。

