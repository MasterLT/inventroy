nohup /home/weigdzc/jdk1.8.0_111/bin/java -jar app.jar >/dev/null 2>&1 &
echo $!>tpid
echo "启动成功"
