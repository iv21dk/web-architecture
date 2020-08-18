# !/bin/bash

echo "Currnt dir: "$PWD
ROOT_DIR=/opt/deploy
SRC_JAR_FILE=$PWD/target/web-architecture-1.0.0-SNAPSHOT.jar
JAR_FILE=$ROOT_DIR/web-architecture.jar

SRC_SERVICE_SCRIPT=$PWD/web-architecture-service.sh
SERVICE_SCRIPT=/etc/init.d/web-architecture.sh

cd ../
git checkout --
git pull
cd backend

echo "Build backend"
mvn clean package -Dmaven.test.skip=true

systemctl stop web-architecture

mkdir $ROOT_DIR
cp -f $SRC_JAR_FILE $JAR_FILE
cp -f $SRC_SERVICE_SCRIPT $SERVICE_SCRIPT

chmod u+x $SERVICE_SCRIPT
systemctl daemon-reload
systemctl start web-architecture



