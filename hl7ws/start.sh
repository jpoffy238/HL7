#!/bin/bash
echo $@
docker 	run \
	-h hl7p \
	--link=actmq1 \
	--link=actmq2 \
	-p 8080:8080 \
	-p 8085:8085 \
	--name hl7ws_8080 \
	-d \
	hl7ws:0.0.7-SNAPSHOT 

