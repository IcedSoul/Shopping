# Shopping
## 简单介绍
本项目使用Spring+SpringMVC+Hibernate来实现了一个网上商城系统，可以使用maven构建为war包进行部署，也可以使用docker直接运行。

## 开发计划
- 在新的分支进行前后端分离，使用Spring boot jpa重构后端。
- 使用vue重构前端。
- 拆分后端为多个服务，引入Spring cloud netflix组件，做好自动化测试。
- 开新分支，引入Spring cloud alibaba组件。
- 增加k8s部署文件，部署istio，jaeger。
- 增加故障注入功能。

## 快速运行

```shell script
# 1.执行下面命令将代码下载到本地（需安装git客户端，如果没有安装则在此页面选择Download as zip 方式下载到本地）
git clone https://github.com/IcedSoul/Shopping.git

# 2.运行项目（使用远程镜像,如果失败不要紧，执行docker-compose down 然后重新docker-compose up即可。）
cd ./Shopping 
docker-compose up -d
```
在http://[server-ip]:8080/Shopping访问主页。

## 编译项目

```shell script
# 进入项目根目录
cd ./Shopping

# 编译war包
mvn clean package -DskipTests

# 构建镜像
docker-compose build

# 启动mysql和shopping
docker-compose up -d
```

## 默认用户
- user: user1
- password: 123

- user: admin
- password: admin

## 主要功能
1. 普通用户
    - 登录、注册功能
    - 浏览商品功能
    - 搜索商品功能
    - 查看商品详情
    - 添加购物车
    - 购买功能（在商品详情页单独购买或在购物车批量购买）
    - 查看订单状态
    - 确认收货功能
    - 评价已购买商品功能
2. 管理员
    - 拥有普通用户所有功能
    - 查看、删除所有用户功能
    - 查看、删除所有商品功能
    - 添加新的商品功能
    - 处理订单功能
    - 发货功能

## 运行截图

![主页](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shoping-main.png)
![商品详情页](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shopping-detail.png)
![购物车](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shoping-car.png)
![订单页](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shoping-order.png)
![订单处理页](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shopping-handle.png)
![控制台](https://xiaofengguo.oss-cn-hangzhou.aliyuncs.com/shoping-main.png)

## 演示地址
http://119.23.212.211:8080/Shopping
