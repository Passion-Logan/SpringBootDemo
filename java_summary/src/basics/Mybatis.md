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

#### 3. javaBean和jdbcType之间的切换需要如何配置

配置直接通过mapper的配置文件，写resultMap即可

```xml
<resultMap id="BaseResultMap" type="com.demo.entity.Test" >
    <id column="TYPE_ID" property="typeId" jdbcType="INTEGER" />
    <result column="TYPE" property="type" jdbcType="SMALLINT" />
    <result column="START_DATE" property="startDate" jdbcType="DATE" />
    <result column="END_DATE" property="endDate" jdbcType="DATE" />
</resultMap>
```