#!/bin/sh
nohup java -Dlogback.configurationFile=logback.xml \
    -Dlog4j.configuration=file:log4j.properties \
    -Djava.ext.dirs=lib \
    -Xms1g -Xmx2g -cp ampq-logback-client-0.0.1-SNAPSHOT.jar \
    com.github.knightliao.consumer.ConsumerMain >/dev/null 2>&1 &