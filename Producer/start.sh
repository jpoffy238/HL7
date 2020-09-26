#!/bin/bash
echo $@
docker 	run \
	-h hl7p \
	--link=actmq1 \
	--link=actmq2 \
	-p $1:8080 \
	--name hl7p_$1 \
	-d \
	hl7_producer:0.1.1 \

