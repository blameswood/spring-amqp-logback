#!/bin/bash

for pid in `ps -ef | grep "ampq-logback-client" | grep -v grep | awk '{print $2}'`
do
    echo killing ${pid}
    kill -s 9 ${pid}
done

