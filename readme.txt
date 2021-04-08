1. 安装Nacos
先下载安装Nacos服务端，启动可进入服务管理页面。

2. SpringCloud整合Nacos
  在服务提供者与消费者中都得配置好服务名称和Nacos地址。
  [注意]：服务名称不要使用下划线，否则在使用uri的时候会报错，可以使用-，如nacos-client。
         比如在网关中配置uri时也适用，使用下划线则会报错找不到服务。
  [Tips]: 测试过程中需要在一个Idea中对一个项目启动多个实例，在Run->Edit Configuration中添加一个Spring Boot实例，选择Main Class为
          nacos-server，同时在启动一个实例后修改配置文件的server.port，与第一个实例不同，然后再启动即可。

（1）服务提供者nacos-server
    加入Nacos的服务注册与发现模块：spring-cloud-starter-alibaba-nacos-discovery
    创建启动类，并实现一个HTTP接口：
    @EnableDiscoveryClient开启Spring Cloud的服务注册与发现，由于引入了spring-cloud-starter-alibaba-nacos-discovery模块，
    所以Spring Cloud Common中定义的那些与服务治理相关的接口将使用Nacos的实现。
（2）服务消费者nacos-client
    实现一个应用来消费上面已经注册到Nacos的服务，编辑pom.xml中的依赖内容，与上面服务提供者的一样即可；
    创建启动类，并实现一个HTTP接口，在该接口中调用服务提供方的接口。
    使用Spring Cloud Common中的LoadBalancerClient接口来挑选服务实例信息。然后从挑选出的实例信息中获取可访问的URI，拼接上服务提供方的接口规则来发起调用。

3. Sentinel
（1）下载控制台jar包
    启动控制台：
    java -Dserver.port=9191 -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=sentinel -jar sentinel-dashboard-1.8.0.jar

（2）SpringCloud接入Sentinel
    pom文件引入spring-cloud-starter-alibaba-sentinel依赖项后即可在控制台实时监控访问数据，前提是
    在配置文件中需要将spring.cloud.sentinel.eager设为false。
    控制台中可以添加流控规则，如资源名：/test，单机阀值：5，然后使用ab工具测试：ab -c 2 -n 10 http://localhost:8084/test
    模拟2个用户共10次请求，则会查看到有5次请求被拒绝。

4. Dubbo
（1）服务提供方
    dubbo-service-api：接口公共包
    dubbo-service-server：实现接口，并配置dubbo服务
（2）服务消费方
    nacos_client：添加dubbo依赖，添加dubbo相关配置，调用接口（test2）
    启动报端口被占用，可在消费方设置：dubbo.protocols.dubbo.port=20881，区别与提供方不同端口号

5. RocketMQ
（1）消息生产者
    rocketmq-producer
（2）消息消费者
    rocketmq-consumer

PS:
1. 项目中经常需要将公共部分提出来打成jar包供其他项目使用，那如何操作呢？
   首先，公共项目不能使用SpringBoot项目，因为SpringBoot会自带打包插件：spring-boot-maven-plugin，这样生成的jar包是不能被直接调用的，
   可以查看jar包的目录结构，类都放在BOOT-INF下的，这样是无法引用的。所以需要改用原生的Maven打包插件：maven-compiler-plugin。
   公共项目pom文件或不需要build，只需要如下配置即可：
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.quanxi</groupId>
       <artifactId>dubbo-service-api</artifactId>
       <name>dubbo-service-api</name>
       <version>0.0.1-SNAPSHOT</version>
   然后执行Maven -> Lifecycle -> install，即可在maven本地库上找到jar包。

[Tips]:
1. Idea右侧Maven中没有子模块，可以点击添加选择子模块的pom文件即可。
2. 如Idea要运行的项目实例不存在，选择Run->Edit Configuration，添加一个SpringBoot项，Main Class选择该项目的主入口程序。

==========================================================
**********************************************************

SpringCloud Alibaba从入门到精通教程
https://blog.csdn.net/hemin1003/article/details/105517901/

Spring Cloud Alibaba 架构实战
https://zhuanlan.zhihu.com/p/100961338