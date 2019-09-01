- [1. 多表联合查询时遇到字段名重复，该如何配置避免报错](#1-多表联合查询时遇到字段名重复该如何配置避免报错)
- [2. MyBatis批量更新集合对象，需要实现什么配置？](#2-mybatis批量更新集合对象需要实现什么配置)
- [3. javaBean和jdbcType之间的切换需要如何配置](#3-javabean和jdbctype之间的切换需要如何配置)


#### 1. 多表联合查询时遇到字段名重复，该如何配置避免报错

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