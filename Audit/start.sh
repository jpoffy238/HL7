#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h audit \
	--link=actmq1 \
	--link=actmq2 \
	--link=/hrcp \
	--name Audit$1 \
	-d \
	audit:0.1.1-SNAPSHOT 

