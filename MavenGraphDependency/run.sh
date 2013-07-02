#!/bin/sh

max=$3
for i in `seq 1 $1`
do
    min=$max
    max=$((min+$2))
    java -Xmx3000m -XX:MaxPermSize=256m -jar target/MiningMaven-1.0-SNAPSHOT-jar-with-dependencies $min $max $4
done
