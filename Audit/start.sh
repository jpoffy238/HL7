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
	-m 256m \
	--memory-swap=0 \
	audit:0.1.2-SNAPSHOT 

