# i-LogisticsSystem

> 2020年东软暑期实训项目 - - 物流配送管理系统

### RocketMQ

#### 服务端启动

1. [使用maven编译](https://rocketmq.apache.org/docs/quick-start/#download--build-from-release) (需要jdk)

2. 修改`bin/mqnamesrv.sh`和`bin/mqbroker`中的内存限制与`JAVA_HOME`设置

3. 修改`conf/broker.conf`中的`namesrvAddr`(IP + port)与`brokerIP1`等设置

4. 后台启动`mqnamesrv`与`mqbroker`(配置`-n` 与 `-c`)

5. 编译配置并启动[`rocketmq-console`](https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console)