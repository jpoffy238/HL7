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
	--mount type=tmpfs,destination=/tmp,tmpfs-mode=777,tmpfs-size=10000000 \
	--name $1 \
	hl7consumerr:0.0.5-SNAPSHOT \
	$1

