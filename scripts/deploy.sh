#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/perfumes.pem \
  target/OnlineShop-1.0-SNAPSHOT.jar \
  ec2-user@ec2-18-157-175-126.eu-central-1.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i ~/.ssh/perfumes.pem ec2-user@ec2-18-157-175-126.eu-central-1.compute.amazonaws.com << EOF
pgrep java | xargs kill -9
nohup java -jar OnlineShop-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'
