#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20230223.sql ./mysql/db
cp ../sql/ry_config_20220929.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../jingli-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy jingli-gateway "
cp ../jingli-gateway/target/jingli-gateway.jar ./jingli/gateway/jar

echo "begin copy jingli-auth "
cp ../jingli-auth/target/jingli-auth.jar ./jingli/auth/jar

echo "begin copy jingli-visual "
cp ../jingli-visual/jingli-monitor/target/jingli-visual-monitor.jar  ./jingli/visual/monitor/jar

echo "begin copy jingli-modules-system "
cp ../jingli-modules/jingli-system/target/jingli-modules-system.jar ./jingli/modules/system/jar

echo "begin copy jingli-modules-file "
cp ../jingli-modules/jingli-file/target/jingli-modules-file.jar ./jingli/modules/file/jar

echo "begin copy jingli-modules-job "
cp ../jingli-modules/jingli-job/target/jingli-modules-job.jar ./jingli/modules/job/jar

echo "begin copy jingli-modules-gen "
cp ../jingli-modules/jingli-gen/target/jingli-modules-gen.jar ./jingli/modules/gen/jar

