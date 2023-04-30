Run below command to generate new key and CSR file. Provide all the information required
```
openssl req -new -newkey rsa:2048 -nodes -keyout <private-key-file-name>.key -out <certificate-file-name>.csr
```

Provide CSR file to certificate authority(CA). CA will approve and provide certificate files.

If PEM, run below command with PEM file and generated key to generate PKCS12 (p12) keystore with alias name, which will be used by spring boot app 
```
openssl pkcs12 -export -in <pem-file-name>.pem -inkey <private-key-file-name>.key -out <pkcs12-file-name>.p12 -name "<alias-name>"
```

If CRT, run below command with certificate files and generated key to generate PKCS12 (p12) keystore with alias name, which will be used by spring boot app
``` 
openssl pkcs12 -export -in <certificate-file-name>.crt -inkey <private-key-file-name>.key -out <pkcs12-file-name>.p12 -name "<alias-name>" -certfile <CA-file-name>.cert
```
