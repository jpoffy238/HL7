#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h audit3 \
	--link=actmq1 \
	--link=actmq2 \
	--link=hrcp \
	--name Audit3 \
	-d \
	audit:0.1.0 

