# hello-springboot-zookeeper

/usr/local/apache-zookeeper-3.9.1-bin/bin

启动：
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh start /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo1.cfg
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh start /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo2.cfg
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh start /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo3.cfg

查看状态：
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh status /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo1.cfg
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh status /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo2.cfg
/usr/local/apache-zookeeper-3.9.1-bin/bin/zkServer.sh status /usr/local/apache-zookeeper-3.9.1-bin/conf/zoo3.cfg

删除节点：
http://127.0.0.1:9998/zookeeper/selectZnode?path=/node2

创建节点：
http://127.0.0.1:9998/zookeeper/createZnode?path=node2