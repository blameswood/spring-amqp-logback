# spring-amqp-logback
Best practice for logging with AMQP+LOGBACK (使用Rabbitmq+logback来中心化存储你的业务日志）

包含 日志发布端 和 日志接收端 两个最佳实践例子。

## 发布消息 usage

发布消息可以采用 Java/Python/PHP 等客户端。

### USE JAVA 

#### rabbit.properties

    host=127.0.0.1
    port=5672
    username=root
    password=123456
    
    routingKeyPattern=test
    exchangeName=logs

#### logback.xml 

    <appender name="AMQP" class="org.springframework.amqp.rabbit.logback.AmqpAppender">
        <layout>
            <pattern>%X{sessionId} %date [%thread] %-5level %logger{80} - %msg%n</pattern>
        </layout>

        <host>${host}</host>
        <port>${port}</port>
        <username>${username}</username>
        <password>${password}</password>
        <routingKeyPattern>${routingKeyPattern}</routingKeyPattern>
        <exchangeName>${exchangeName}</exchangeName>

        <declareExchange>true</declareExchange>
        <generateId>true</generateId>
        <charset>UTF-8</charset>
        <durable>true</durable>
        <deliveryMode>PERSISTENT</deliveryMode>
    </appender>
    
    <root>
        <level value="INFO"/>
        <appender-ref ref="AMQP"/>
    </root>
    
#### pom

    <properties>
        <spring.amqp.version>1.4.5.RELEASE</spring.amqp.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-amqp</artifactId>
            <version>${spring.amqp.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit</artifactId>
            <version>${spring.amqp.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-core</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-messaging</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-tx</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

## 消费消息

使用 java程序来进行 消息订阅，消息订阅后立马从broker中删除。

目录位于client-package下 ampq-logback-client-*.tar.gz

### 第一步：解压文件 ampq-logback-client-*.tar.gz

解压后是文件列表是：

    ampq-logback-client-0.0.1-SNAPSHOT.jar    
    log4j.properties                          
    start.sh
    logback.xml
    lib                                       
    mq.properties
    
### 第二步：修改 mq配置

    host=127.0.0.1
    port=5673
    username=root
    password=123456
    
    queue=testLog

### 第三步：启动程序

sh start.sh

### 第四步：关闭程序

sh stop.sh

### 生成日志格式
    
    log
        - 2015-06
            - amqp.2015-06-01.log
            - amqp.2015-06-02.log
            - .....
        - 2015-07
        - ........
        
### 出错处理

查看 logs/amqp_client_log4j.log 查看问题

## 联系·讨论

java技术QQ群：68373211

## 依赖jar包

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