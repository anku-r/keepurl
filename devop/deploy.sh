cd ..
git pull origin main
mvn clean install -Dmaven.test.skip=true
export MONGO_KEEPURL_DATASOURCE=`cat /appinfo/MONGO_KEEPURL_DATASOURCE`
java -jar target/keepurl-0.0.1-SNAPSHOT.jar
