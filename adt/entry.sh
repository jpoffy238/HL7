#!/bin/sh
echo Args passwed "$@"
echo "$PATH"
type java

java -jar -Dspring.activemq.queue=$1 /app.jar
