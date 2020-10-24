#!/bin/bash
echo $@
set -x
docker  \
	run \
	-h $1 \
	--link=/actmq1 \
	--link=/actmq2 \
	--link=/hrcp \
	-d \
	-m 512m \
	--memory-swap=0 \
	--mount type=tmpfs,destination=/tmp,tmpfs-mode=777,tmpfs-size=10000000 \
	--name $1_$2 \
	adt:0.0.6-SNAPSHOT \
	$1

