# Getting Started
## Spring LDAP 示例代码
文档地址: https://docs.spring.io/spring-ldap/docs/2.3.5.RELEASE/reference/
### plain和odm
#### plain
基本操作
#### odm
使用对象映射工具(Object-Directory Mapping)操作
### 认证(默认不开启)
1. 开启用户认证需开启以下代码
```java
1. com.learn.ldap.config.LdapAuthConfig
认证配置,默认使用cn={0},ou=company1,c=CN来搜索用户
2. com.learn.ldap.controller.AuthController
3. pom.xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-ldap</artifactId>
</dependency>
```
2. 开启后需在请求头加上Basic认证信息
username: cn
password: userPassword

