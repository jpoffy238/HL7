#!/bin/bash

queue=100
docker --host vulcan container list | grep adt > qlist

while [ $queue -lt 120 ]
do
	grep adt_${queue} qlist > /dev/null
	if [ $? -eq 0 ]
		then
			echo "Queue running $queue"
	else
		echo "Starting Queue for adt_$queue"

		./start.sh adt_$queue 
	fi
	
	((queue++))
done
