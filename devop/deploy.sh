echo "Deploying KeepURL Application"

echo "Setting environment variables"
export MONGO_KEEPURL_DATASOURCE=`cat /appinfo/prop/MONGO_KEEPURL_DATASOURCE`
export LOG_LEVEL=`cat /appinfo/prop/LOG_LEVEL`

echo "Pulling latest code and Firing Build"
git pull origin main
mvn clean install -Dmaven.test.skip=true

echo "Stopping application"
port=`ps -ef | awk '$10 ~ /keepurl/{print $2}'`
if [ "$port" != "" ] 
then
    kill $port
fi 

echo "Starting application"
java -jar -Ddevop.dir=$PWD/devop target/keepurl-0.0.1-SNAPSHOT.jar