echo "Deploying Keep URL Application"

echo "Pulling latest source from repository using branch: main"
cd ..
git pull origin main

echo "Firing maven build"
mvn clean install -Dmaven.test.skip=true

echo "Setting Data Source"
export MONGO_KEEPURL_DATASOURCE="mongodb+srv://ankur:Dmth26587@keep-url.p75hm.mongodb.net/keepurldb?retryWrites=true&w=majority"

echo "Stopping application"
kill `ps -ef | grep "keepurl" | awk 'NR==1{ print $2 }'`

echo "Removing old log file"
cd devop
rm nohup.out

echo "Starting application"
nohup java -jar ../target/keepurl-0.0.1-SNAPSHOT.jar

echo "Deployment Complete"
