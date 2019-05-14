# Shopping
## 简单介绍
　　本项目使用Spring+SpringMVC+Hibernate来实现了一个网上商城系统。
## 运行环境
    JDK1.7+,Tomcat 8.0或以上,Maven 3.0
## 运行

```shell
# 1.执行下面命令将代码下载到本地（需安装git客户端，如果没有安装则在此页面选择Download as zip 方式下载到本地）
git clone https://github.com/IcedSoul/Shopping.git

# 2.在本地或者远程MySQL数据库中执行Shopping/src/main/resources/properties/路径下的Shooping SQL语句以创建数据库。（需本机安装MySQL，版本建议在5.7或以下，高版本连接会出问题。执行SQL语句可使用命令行或者Navicat等工具。）

# 3.打开Shopping/src/main/resources/properties/路径下的database.properties配置文件，把数据库连接地址，用户名和密码改成自己的

# 4.执行以下maven命令（需本地安装Maven）
mvn clean
mvn package

# 5.在生成的target目录下即可找到对应的Shopping.war包

# 6.将war包复制到Tomcat目录下的webapps文件夹下，重启Tomcat服务器（需要下载Tomcat，版本在8.0或者以上）

# 7.访问 http://localhost:8080/Shopping 来查看效果。（没有任何商品，需要自己手动去添加。注册普通用户，然后在数据库中将用户role字段改为1即可访问控制台页面，进行商品的增加和删除。）
```
## 主要技术
　　JavaEE框架：Spring+SpringMVC+Hibernate<br/>
　　前端框架：Bootstrap<br/>
　　插件：Layer.js,ajaxfileupload.js<br/>
　　数据库：MySQL<br/>
## 主要功能
1. 普通用户
* 登录、注册功能
* 浏览商品功能
* 搜索商品功能
* 查看商品详情
* 添加购物车
* 购买功能（在商品详情页单独购买或在购物车批量购买）
* 查看订单状态
* 确认收货功能
* 评价已购买商品功能
2. 管理员：
* 拥有普通用户所有功能
* 查看、删除所有用户功能
* 查看、删除所有商品功能
* 添加新的商品功能
* 处理订单功能
* 发货功能
## 运行截图

![主页](http://img.icedsoul.cn/img/blog/shopping/main_page.png)
![商品详情页](http://img.icedsoul.cn/img/blog/shopping/product_detail.png)
![购物车](http://img.icedsoul.cn/img/blog/shopping/shopping_car.png)
![订单状态](http://img.icedsoul.cn/img/blog/shopping/order_page.png)
![订单处理](http://img.icedsoul.cn/img/blog/shopping/order_handle.png)
![控制台1](http://img.icedsoul.cn/img/blog/shopping/control_page1.png)
![控制台2](http://img.icedsoul.cn/img/blog/shopping/control_page2.png)

## 演示地址
　　演示网址：http://119.23.212.211:8080/Shopping
