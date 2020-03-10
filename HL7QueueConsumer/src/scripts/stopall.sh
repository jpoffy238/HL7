#!/bin/bash

docker --host vulcan container list | grep adt | awk '{print $1}' |  while read line 
do
	echo "Stopping $line"
	docker --host vulcan stop $line 
done
