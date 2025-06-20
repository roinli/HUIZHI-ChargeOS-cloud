#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}

echo "begin package "
#打包开始
cd ..
mvn clean install -Dmaven.test.skip=true
#前端
cd ./hcp-ui
npm install --registry=https://registry.npmmirror.com
npm run build:prod
cd ../docker
# copy sql
echo "begin copy sql "
cp ../sql/hcp_cloud.sql ./mysql/db
cp ../sql/hcp_config.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../hcp-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy hcp-gateway "
cp ../hcp-gateway/target/hcp-gateway.jar ./hcp/gateway/jar

echo "begin copy hcp-auth "
cp ../hcp-auth/target/hcp-auth.jar ./hcp/auth/jar

echo "begin copy hcp-demo "
cp ../hcp-demo/target/hcp-demo.jar ./hcp/demo/jar

echo "begin copy hcp-monitor "
cp ../hcp-visual/hcp-monitor/target/hcp-monitor.jar  ./hcp/visual/monitor/jar

echo "begin copy hcp-system "
cp ../hcp-modules/hcp-system/target/hcp-system.jar ./hcp/modules/system/jar

echo "begin copy hcp-file "
cp ../hcp-modules/hcp-file/target/hcp-file.jar ./hcp/modules/file/jar

echo "begin copy hcp-gen "
cp ../hcp-modules/hcp-gen/target/hcp-gen.jar ./hcp/modules/gen/jar

echo "begin copy hcp-job "
cp ../hcp-modules/hcp-job/target/hcp-job.jar ./hcp/modules/job/jar

echo "begin copy hcp-mp "
cp ../hcp-modules/hcp-mp/target/hcp-mp.jar ./hcp/modules/mp/jar

echo "begin copy hcp-simulator "
cp ../hcp-modules/hcp-simulator/target/hcp-simulator.jar ./hcp/modules/simulator/jar

echo "begin copy hcp-operator "
cp ../hcp-modules/hcp-operator/target/hcp-operator.jar ./hcp/modules/operator/jar

