#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h audit4 \
	--link=actmq1 \
	--link=actmq2 \
	--link=hrcp \
	--name Audit4 \
	-d \
	audit:0.0.3 

