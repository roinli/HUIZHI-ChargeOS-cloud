#!/bin/sh

# 使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [port|base|modules|stop|rm]"
	exit 1
}

# 开启所需端口
port(){
	firewall-cmd --add-port=80/tcp --permanent
	firewall-cmd --add-port=38080/tcp --permanent
	firewall-cmd --add-port=8848/tcp --permanent
	firewall-cmd --add-port=9848/tcp --permanent
	firewall-cmd --add-port=9849/tcp --permanent
	firewall-cmd --add-port=9999/tcp --permanent
	firewall-cmd --add-port=6379/tcp --permanent
	firewall-cmd --add-port=3306/tcp --permanent
	firewall-cmd --add-port=39100/tcp --permanent
	firewall-cmd --add-port=39200/tcp --permanent
	firewall-cmd --add-port=39201/tcp --permanent
	firewall-cmd --add-port=39202/tcp --permanent
	firewall-cmd --add-port=39203/tcp --permanent
	firewall-cmd --add-port=39300/tcp --permanent
	firewall-cmd --add-port=39204/tcp --permanent
	firewall-cmd --add-port=39206/tcp --permanent
	firewall-cmd --add-port=39207/tcp --permanent
	firewall-cmd --add-port=39028/tcp --permanent
	service firewalld restart
}

# 启动基础环境（必须）
base(){
	docker-compose up -d hcp-mysql hcp-redis hcp-nacos
}

# 启动程序模块（必须）
modules(){
	docker-compose up -d hcp-nginx hcp-gateway hcp-auth hcp-system hcp-gen hcp-file hcp-monitor hcp-demo hcp-job hcp-mp hcp-operator hcp-simulator
}

# 关闭所有环境/模块
stop(){
	docker-compose stop
}

# 删除所有环境/模块
rm(){
	docker-compose rm
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"base")
	base
;;
"modules")
	modules
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac
