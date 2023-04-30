echo "Preparing environment to run KeepURL. Respond to prompt."
cd /home/keepurl

apt-get update && apt-get upgrade
apt-get install openjdk-11-jdk
apt-get install maven
apt-get install nginx

mkdir -p /appinfo/keepurl
mkdir /appinfo/keepurl/log /appinfo/keepurl/prop

read -p "Enter Mongo Datasource URL: " mongo_url
read -p "Enter Log Level: " log_level
read -p "Enter Certificate Alias: " cert_alias
read -p "Enter Certificate Password: " cert_pass
read -p "Enter Certificate Directory: " cert_dir

prop_file=/appinfo/keepurl/prop/envconfig.properties
touch $prop_file
echo "MONGO_KEEPURL_DATASOURCE=$mongo_url" >> $prop_file
echo "LOG_LEVEL=$log_level" >> $prop_file
echo "KEEPURL_KEYSTORE_ALIAS=$cert_alias" >> $prop_file
echo "KEEPURL_KEYSTORE_PASSWORD=$cert_pass" >> $prop_file
echo "KEEPURL_KEYSTORE_PATH=$cert_dir/$cert_alias.p12" >> $prop_file

mkdir $cert_dir
openssl req -new -newkey rsa:2048 -nodes -keyout $cert_dir/$cert_alias.key -out $cert_dir/$cert_alias.csr
read -p "Provide CSR key content to Certificate Authority (CA). Press enter if done!"

cp devop/nginx.conf /etc/nginx
mkdir -p /home/nginx-home/.well-known/pki-validation
read -p "Upload CA authentication file as instructed to verify server. Press enter if done!"
nginx -s reload

read -p "Verify server at CA and upload certificates files in certificate directory. Press enter if done!"
nginx -s stop
openssl pkcs12 -export -in $cert_dir/certificate.crt -inkey $cert_dir/$cert_alias.key -out $cert_dir/$cert_alias.p12 -name "$cert_alias" -certfile $cert_dir/ca_bundle.crt

echo "Environment prepared. Running deployment"
cd ..
nohup devop/deploy.sh


