#!/bin/bash
#Deployment Script. Always run this script from project directory

PRETEXT=[INFO]
LOGDIR=/appinfo/keepurl/log
PROPDIR=/appinfo/keepurl/prop

if [ "$1" == "fileout" ] 
then
    exec > $LOGDIR/build.log
fi

echo $PRETEXT "Running Deployment:" `date` 

echo $PRETEXT "Setting environment variables"
export MONGO_KEEPURL_DATASOURCE=`cat $PROPDIR/MONGO_KEEPURL_DATASOURCE`
export LOG_LEVEL=`cat $PROPDIR/LOG_LEVEL`
export KEEPURL_KEYSTORE_PATH=`cat $PROPDIR/KEEPURL_KEYSTORE_PATH`
export KEEPURL_KEYSTORE_PATH=`cat $PROPDIR/KEEPURL_KEYSTORE_PASSWORD`
export KEEPURL_KEYSTORE_PATH=`cat $PROPDIR/KEEPURL_KEYSTORE_ALIAS`

echo $PRETEXT "Pulling latest code and Firing Build"
git pull origin main
mvn clean install -Dmaven.test.skip=true

echo $PRETEXT "Stopping application"
port=`ps -ef | awk '$10 ~ /keepurl/{print $2}'`
if [ "$port" != "" ] 
then
    kill $port
fi 

echo $PRETEXT "Starting application. Check sysout.log for more information"
if [ "$1" == "fileout" ] 
then
    exec >&-
    #Removing unwanted text from build.log file 
    sed -i -e 's/\\[1;34m//g' -e 's/\\[m//g' -e 's/\\[1m//g' -e 's/\\[0;36m//g' -e 's/\\[0;1m//g' -e 's/\\[0;32m//g' -e 's/\\[36m//g' -e 's/\\[0;1m//g' -e 's/\\[1;32m//g' $LOGDIR/build.log	
fi
java -jar target/keepurl-0.0.1-SNAPSHOT.jar