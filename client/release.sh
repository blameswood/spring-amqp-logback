#!/bin/sh

cd ..
mkdir -p client-package
rm -rf client-package/*
cp client/target/ampq-logback-client*.tar.gz client-package/