#!/bin/bash

queue=100
docker --host vulcan container list | grep adt > qlist

while [ $queue -lt 1000 ]
do
	grep adt_${queue} qlist > /dev/null
	if [ $? -eq 0 ]
		then
			echo "Queue running adt_$queue"
	else
		echo "Queue STOPPED $queue"

		#echo "docker --host vulcan run -d hl7q:0.0.1 adt_${queue}"
		#docker --host vulcan run -d hl7q:0.0.1 adt_${queue} & 
	fi
	
	((queue++))
done
