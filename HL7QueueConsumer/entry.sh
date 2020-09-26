#!/bin/sh
echo Args passwed "$@"
echo "$PATH"
type java

java -jar -Dspring.activemq.queue=$1 /opt/hl7Q.jar
