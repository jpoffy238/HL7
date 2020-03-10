#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h audit \
	--link=actmq1 \
	--link=hrcp \
	--name Audit \
	-d \
	audit:0.1.0 

