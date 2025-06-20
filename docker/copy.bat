@echo off
echo.
echo [信息] 复制文件到Docker目录
echo.

%~d0
cd %~dp0

cd ..
echo 编译后端
start /wait cmd /c "mvn clean package -P prod -Dmaven.test.skip=true"
echo 编译前端
cd vctgo-ui
start /wait cmd /c "npm install"
start /wait cmd /c "npm run build:prod"
cd ..\docker

echo 复制 sql
xcopy ..\sql\hcp_cloud.sql .\mysql\db  /y
xcopy ..\sql\hcp_config.sql .\mysql\db  /y

echo 复制 html
xcopy ..\hcp-ui\dist .\nginx\html\dist  /s /e /y

echo 复制 hcp-gateway
xcopy ..\hcp-gateway\target\hcp-gateway.jar .\hcp\gateway\jar  /y

echo 复制 hcp-auth
xcopy ..\hcp-auth\target\hcp-auth.jar .\hcp\auth\jar  /y

echo 复制 hcp-demo
xcopy ..\hcp-demo\target\hcp-demo.jar .\hcp\demo\jar  /y

echo 复制 hcp-monitor
xcopy ..\hcp-visual\hcp-monitor\target\hcp-monitor.jar  .\hcp\visual\monitor\jar  /y

echo 复制 hcp-system
xcopy ..\hcp-modules\hcp-system\target\hcp-system.jar .\hcp\modules\system\jar  /y

echo 复制 hcp-file
xcopy ..\hcp-modules\hcp-file\target\hcp-file.jar .\hcp\modules\file\jar  /y

echo 复制 hcp-gen
xcopy ..\hcp-modules\hcp-gen\target\hcp-gen.jar .\hcp\modules\gen\jar  /y

echo 复制 hcp-job
xcopy ..\hcp-modules\hcp-job\target\hcp-job.jar .\hcp\modules\job\jar  /y

echo 复制 hcp-mp
xcopy ..\hcp-modules\hcp-mp\target\hcp-mp.jar .\hcp\modules\mp\jar  /y

echo 复制 hcp-operator
xcopy ..\hcp-modules\hcp-operator\target\hcp-operator.jar .\hcp\modules\operator\jar  /y

echo 复制 hcp-simulator
xcopy ..\hcp-modules\hcp-simulator\target\hcp-simulator.jar .\hcp\simulator\gen\jar  /y


pause
