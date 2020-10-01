#!/bin/sh
ls -altr /app.jar
sha256sum /app.jar

java -jar /app.jar 
