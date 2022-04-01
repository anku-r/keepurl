echo "Deploying Keep URL Application"

echo "Pulling latest source from repository using branch: main"
cd ..
git pull origin main

echo "Executing maven build"
mvn clean install -Dmaven.test.skip=true

echo "Exporting Datasource"
export MONGO_KEEPURL_DATASOURCE=`cat /env_prop/MONGO_KEEPURL_DATASOURCE`

echo "Stopping application"
kill `ps -ef | awk '$10 ~ /keepurl/{print $2}'`

echo "Removing old log file"
cd devop
rm nohup.out

echo "Starting application"
nohup java -jar -Ddevop.dir=$PWD ../target/keepurl-0.0.1-SNAPSHOT.jar

echo "Deployment Complete"
