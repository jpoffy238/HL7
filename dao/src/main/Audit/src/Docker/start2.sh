#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h audit2 \
	--link=actmq1 \
	--link=actmq2 \
	--link=hrcp \
	--name Audit2 \
	-d \
	audit:0.1.0 

