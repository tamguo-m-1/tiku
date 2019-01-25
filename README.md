# tamguo 題庫

![logo](https://images.gitee.com/uploads/images/2019/0110/164645_73f76413_93398.png "logo")

# 简介

`探果网`（简称tamguo）是基于java开发的在线题库系统，包括

 1. [在线访问][1]
 2. [后台运营][2]
 3. [会员中心][3]
 4. [书籍中心][4]

  [1]: http://www.tamguo.com
  [2]: http://admin.tamguo.com
  [3]: http://member.tamguo.com
  [4]: http://book.tamguo.com

- 管理员账号：system 密码：123456  **因为线上数据和测试数据没有做到隔离，作者已经把密码修改，可用.sql在本地运行看后台效果。** 

QQ群：937899574



松耦合、高可用、可靠一直是软件工程的设计目标，探果网在这些方面还有很长的路要走，期待我们能手牵手一起走向未来。

> ### 期望
> 作者每天会把当天需要做的事情列出一个清单，给这周或者这个月立下一个flag。但是这中间发现了一些问题，事情永远也做不完，只会越做越多。

探果网期望可以使用java开发一套完整的互联网项目，分享互联网在技术、运营上的经验和知识。


# 系统部署图

# 项目结构

![探果网项目结构](https://images.gitee.com/uploads/images/2019/0110/164645_39231d0a_93398.png "探果网项目结构")

1. `tamguo-common` 一些基础的工具类包
2. `tamguo-modules-core` 依赖`tamguo-common`，主要是核心业务包，包括数据处理，文件处理，邮件处理，短信处理等等。
3. `tamguo-bms` 书籍系统[https://book.tamguo.com](https://books.tamguo.com "https://books.tamguo.com"),书籍系统展现层
4. `tamguo-mms`会员中心[https://member.tamguo.com](https://member.tamguo.com "https://member.tamguo.com")
5. `tamguo-oms`后台管理系统[https://admin.tamguo.com](https://member.tamguo.com "https://admin.tamguo.com")
6. `tamguo-crawler`爬虫程序，单独项目运行

# 数据库脚本

在`tamguo db`目录下


### 开始


> 安装 `redis`

因为官网没有`windows redis`的版本,我们去github上下载windows版本的redis

[redis windows 下载](https://github.com/MicrosoftArchive/redis/releases "redis window")

下载`Redis-x64-3.2.100.zip`这个包，解压，执行`redis-server.exe`。

![redis 启动](https://images.gitee.com/uploads/images/2019/0111/104127_b33cb3c0_93398.png "redis 启动")

看到这个界面Redis就算启动成功。


> 安装 `MYSQL`

不做描述, 把tamguo下面的db文件导入mysql注意数据库名称。

![db 数据](https://images.gitee.com/uploads/images/2019/0111/104127_1a7b4fe4_93398.jpeg "db 数据")

> 安装 `jdk1.8`

不做描述

至此`tamguo`的环境基本基本上已经安装好。

### 启动 tms

找到`tamguo-tms`下面的`application.propertys`。 

- domain.name // 页面引用静态资源的前缀
- member.domain.name // 会员系统跳转域名
- cookie.domian.name // 关系到session，本地配置成local
- server.port     // 服务端口
- spring.datasource.url   // 数据库连接地址
- spring.datasource.username // 数据库连接用户名
- redis.hostname    // redis 服务地址
- redis.port        // redis 端口
- file.storage.path  // 上传文件存放路径

确定`propertys`正确后，用springboot 方式启动应用。

![启动成功](https://images.gitee.com/uploads/images/2019/0111/104128_d1901664_93398.png "启动成功")

到这里就启动成功了，访问 `http://localhost:8081/`

到现在tms已经启动成功， tms 针对的用户（包括老师，学生等等）。

![tamguo](https://images.gitee.com/uploads/images/2019/0111/104127_fbd92553_93398.png "tamguo")


> 启动 bms （书籍项目）

和tms一样配置好`application.propertys`,启动application.java 即可。

![book 探书网](https://images.gitee.com/uploads/images/2019/0111/104127_66c81c50_93398.jpeg "book 探书网")

> 启动 mms （会员系统）

和tms一样配置好`application.propertys`,启动application.java 即可。

![会员中心](https://images.gitee.com/uploads/images/2019/0111/104127_5fc49cdc_93398.jpeg "会员中心")

> 启动 oms （后台运营系统）

和tms一样配置好`application.propertys`,启动application.java 即可。

![oms](https://images.gitee.com/uploads/images/2019/0111/104127_e819fb3d_93398.jpeg)

到这里`tamguo`的所有子系统都已经启动成功

> ### 捐赠 
> 感谢你们的支持！
