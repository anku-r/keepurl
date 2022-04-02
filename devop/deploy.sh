cd ..
git pull origin main
mvn clean install -Dmaven.test.skip=true
export MONGO_KEEPURL_DATASOURCE=`cat /appinfo/prop/MONGO_KEEPURL_DATASOURCE`
export LOG_LEVEL=`cat /appinfo/prop/LOG_LEVEL`
java -jar target/keepurl-0.0.1-SNAPSHOT.jar
