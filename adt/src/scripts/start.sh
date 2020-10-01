#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h $1 \
	--link=actmq1 \
	--link=actmq2 \
	--link=hrcp \
	-d \
	--name $1 \
	hl7q:0.1.1 \
	$1

