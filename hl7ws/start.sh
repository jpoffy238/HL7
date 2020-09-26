#!/bin/bash
echo $@
docker 	run \
	-h hl7p \
	--link=actmq1 \
	--link=actmq2 \
	-p $1:8080 \
	--name hl7ws_$1 \
	-d \
	hl7ws:0.0.3-SNAPSHOT 

