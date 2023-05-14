springboot写的一个前后不分离的博客

## 1,环境

前后不分离，渲染模板thymeleaf 

开发工具 idea

环境：springboot+mybatis+maven

部署：docker



## 2,数据库sql脚本

```sql
create database blog;
use blog;
show variables like 'SQL_SAFE_UPDATES';
set SQL_SAFE_UPDATES=0;

create table blog_tag(blogid long ,tagid long); 

create table Blog
(id BIGINT primary key,
title varchar(32),
content text,
firstPicture varchar(64),
flag varchar(32),
views int,
appreciation boolean,
shareStatement boolean,
commentabled boolean,
published boolean,
recommend boolean,
createTime datetime,
updateTime datetime,
typename varchar(32),
userid long,
description varchar(10383)
);
create table Type(id long,name varchar(32) primary key);

create table tag(id long,name varchar(32) primary key);

create table user(
id long,
nickname varchar(15),
username varchar(15),
password varchar(200),
email varchar(32),
avatar varchar(64),
type int,
createTime datetime,
updateTime datetime
);

create table comment(
id long,
nickname varchar(15),
email varchar(32),
content varchar(10380) null,
avatar varchar(64),
createTime datetime,
blogid long,
parentid long,
admincomment boolean 
);

#管理员数据插入:密码处理工具在项目的工具包中
insert into user value(id,"用户昵称","用户账号",
"md5工具处理后的密码，由于没开放，注册功能，请用util包中工具类生成！然后填入数据库","邮箱","头像图片地址",
类型编号,now(),now());
```



## 3,功能


### 3.1 后台管理

#### 登录界面

后台管理页面的账号验证使用拦截器通过session完成验证。存入数据库的用户密码用一个MD5工具进行了转换，防止明文存放密码。登录成功会在整个后台的页面右上角，展示登录账号的头像。可以通过点击头像完成账号的退出。

![1673615890700](/images/1673615890700.png)

登录成功界面

![1673615923415](/images/1673615923415.png)

退出登录

![1673616662882](/images/1673616662882.png)

#### 博客管理界面

博主管理博客的页面，展示博客一些关键的属性。可以进行博客搜索，博客编辑，修改，添加。

![1673616271606](/images/1673616271606.png)

#### 分类管理管理

对分类进行查看，对分类进行编辑，删除，添加等功能。

![1673616377660](/images/1673616377660.png)

#### 标签管理界面

管理标签，对标签的增删改查功能。

![1673616516863](/images/1673616662882.png)

### 3.2 前台

#### 首页

查看最新推荐的10篇博客

![1673616865846](/images/1673616865846.png)

#### 分类

按照分类查看博客

![1673616889186](/images/1673616889186.png)

#### 标签

按照标签查看博客

![1673616937499](/images/1673616662882.png)

#### 归档

按照年份统计博客

![1673616963704](/images/1673616963704.png)

#### 关于我

自我介绍页面

![1673616987064](/images/1673616987064.png)





## 4 部署

docker部署mysql

使用mysql8

```
docker pull mysql
docker run -d -p 0.0.0.0:3306:3306 -e  MYSQL_USER="ekko" -e MYSQL_PASSWORD="1234" -v /home/mysql/data:/var/lib/mysql  --name blogmysql mysql
```



制作博客镜像和运行

Dockerfile

```
FROM java:8
MAINTAINER ekko <189890049@qq.com>
COPY ./myblog-0.0.1-SNAPSHOT.jar /temp/myblog.jar
EXPOSE 8080
# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT java -jar /temp/myblog.jar
```

镜像制作和运行

```
docker build -t blog:1.0 .
docker run --name myblog -p 80:80 -v /etc/blog/log:/log -d myblogbyjava8:1.0
```



