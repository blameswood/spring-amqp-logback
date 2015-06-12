# spring-amqp-logback
Best practice for logging with AMQP+LOGBACK 

包含 日志发布端 和 日志接收端 两个最佳实践例子。

### 发布消息 usage

    <appender name="AMQP" class="org.springframework.amqp.rabbit.logback.AmqpAppender">
        <layout>
            <pattern>%date [%thread] %-5level %logger{80} - %msg%n</pattern>
        </layout>

        <host>127.0.0.1</host>
        <port>5672</port>
        <username>root</username>
        <password>123456</password>
        <routingKeyPattern>test</routingKeyPattern>
        <exchangeName>logs</exchangeName>

        <declareExchange>true</declareExchange>
        <generateId>true</generateId>
        <charset>UTF-8</charset>
        <durable>true</durable>
        <deliveryMode>PERSISTENT</deliveryMode>
    </appender>

### 消费消息 client jar 

目录位于client-package下 ampq-logback-client-*.tar.gz

#### 第一步：解压文件 ampq-logback-client-*.tar.gz

解压后是文件列表是：

    ampq-logback-client-0.0.1-SNAPSHOT.jar    
    log4j.properties                          
    start.sh
    logback.xml
    lib                                       
    mq.properties
    
#### 第二步：修改 mq配置

    host=10.46.152.32
    port=8092
    username=root
    password=MhxzKhl
    
    queue=testLog

#### 第三步：启动程序

sh start.sh

#### 第四步：关闭程序

通过  

    ps aux | grep ampq-logback-client

找到pid

然后kill掉

#### 生成日志格式
    
    logs
        - 201506
            - amqp.20150601.log
            - amqp.20150602.log
            - .....
        - 201507
        - ........
            

### 依赖jar包

    [INFO] com.github.knightliao.logback:ampq-logback-client:jar:0.0.1-SNAPSHOT
    [INFO] +- org.springframework.amqp:spring-amqp:jar:1.4.5.RELEASE:compile
    [INFO] +- org.springframework.amqp:spring-rabbit:jar:1.4.5.RELEASE:compile
    [INFO] |  +- com.rabbitmq:amqp-client:jar:3.5.1:compile
    [INFO] |  \- org.springframework.retry:spring-retry:jar:1.1.2.RELEASE:compile
    [INFO] +- org.codehaus.jackson:jackson-mapper-asl:jar:1.9.13:runtime
    [INFO] |  \- org.codehaus.jackson:jackson-core-asl:jar:1.9.13:runtime
    [INFO] +- org.springframework:spring-core:jar:3.1.2.RELEASE:compile
    [INFO] |  \- org.springframework:spring-asm:jar:3.1.2.RELEASE:compile
    [INFO] +- org.springframework:spring-context:jar:3.1.2.RELEASE:compile
    [INFO] |  +- org.springframework:spring-aop:jar:3.1.2.RELEASE:compile
    [INFO] |  +- org.springframework:spring-beans:jar:3.1.2.RELEASE:compile
    [INFO] |  \- org.springframework:spring-expression:jar:3.1.2.RELEASE:compile
    [INFO] +- org.springframework:spring-tx:jar:3.1.2.RELEASE:compile
    [INFO] |  \- aopalliance:aopalliance:jar:1.0:compile
    [INFO] +- commons-io:commons-io:jar:1.4:compile
    [INFO] +- commons-lang:commons-lang:jar:2.4:compile
    [INFO] +- commons-logging:commons-logging:jar:1.0.4:compile
    [INFO] +- org.slf4j:slf4j-api:jar:1.7.6:compile
    [INFO] +- ch.qos.logback:logback-core:jar:1.0.9:compile
    [INFO] +- ch.qos.logback:logback-classic:jar:1.0.9:compile
    [INFO] \- log4j:log4j:jar:1.2.15:compile