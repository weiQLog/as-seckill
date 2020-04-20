# 分布式高并发限时抢购秒杀系统

### 简介

通过SpringBoot快速搭建的前后端分离的电商秒杀项目，项目基于SpringBoot Alibaba 的微服务项目，其中包括商品库存服务，秒杀服务和Redis缓存服务

### 项目环境

JDK1.8, Maven, MySQL, IntelliJ IDEA, SpringBoot 2.2.2.RELEASE, Redis, Ubuntu 16.4

### 启动说明

* redis: 修改redis.conf

  ```config
  bind 0.0.0.0 						# 绑定IP
  port 7001  							# 绑定端口
  daemonize yes  						# 后台运行
  pidfile /redis/redis-7001 			# 进程文件名
  logfile /redis/7001/redis7001.log 	# 日志文件名
  dir /redis/7001/ 					# 数据文件存放地址
  cluster-enabled yes 				# 启用集群
  cluster-config-file nodes-7001.conf # 配置每个节点的配置文件
  cluster-node-timeout 15000 			# 配置每个节点的配置文件
  appendonly yes 						# 持久化aof策略
  appendfsync always					# 每写操作都记录日志
  ```

  * 启动每个所有Redis 
    * 启动集群： redis-cli --cluster create 192.168.0.101:7001,192.168.0.101:7001 ... --cluster-replicas 1  -a pwd

* nacos: 集群配置

  * 修改Nacos startup.sh文件

  ```bash
  while getopts ":m:f:s:p:" opt
  do
  	case $opt in
  		m)
  			MODE=$OPTARG;;
  		f)
  			FUNCATION_MODE=$OPTARG;;
  		s)
  			SERVER=$OPTARG;;
  		p)
  			PORT=$OPTARG;;
  		?)
  		echo "Unknow parameter"
  		exit 1;;
  	esac
  done
  nohup $JAVA ·Dserver.port=${PORT} ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/log/start.out 2>&1 &
  ```
  * 启动nacos

    ```bash
    ./startup.sh -p 3333
    ./startup.sh -p 4444
    ./startup.sh -p 5555
    ```

  * 通过nginx 帮助实现 nacos的集群

    * 修改nginx.conf

      ```
      upstream cluster {
      	server 127.0.0.1:3333;
      	server 127.0.0.1:4444;
      	server 127.0.0.1:5555;
      }
      server {
      	listen 1111,
      	server_name localhost;
      	location /{
      		proxy_pass http://cluster;
      	}
      }
      ```

  * 启动nginx 访问 http://127.0.0.1:1111/nacos

* 启动sentinel: java -jar sentinel-dashboard-1.7.0.jar

* 暴露端口： sudo ./openPort.sh -p 3333,4444,5555,8080,1111

  ```bash
  #!/bin/bash
  while getopts ":p:" opt
  do
  	case $opt in
  		p)
  			str=$OPTARG
  			IFS=","
  			arr=($str)
  			for arg in ${arr[*]}
  			do
  				sudo iptables -I INPUT -p tcp --dport $arg -j ACCEPT
  			done
  			;;
  		?)
  		echo "Unknown parameter"
  		exit 1;;
  	esac
  done
  ```

  chmod +x ./openPort.sh

* seata 下载https://seata.io/zh-cn/blog/download.html

  * 新建 seata 库 https://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html

  * file.conf

    ```
    service {
      #transaction service group mapping
      vgroup_mapping.my_test_tx_group = "my_tx_group"
      #only support when registry.type=file, please don't set multiple addresses
      default.grouplist = "127.0.0.1:8091"
      #disable seata
      disableGlobalTransaction = false
    }
    
    ## transaction log store, only used in seata-server
    store {
      ## store mode: file、db
      mode = "db"
    
      ## file store property
      file {
        ## store location dir
        dir = "sessionStore"
      }
    
      ## database store property
      db {
        ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
        datasource = "dbcp"
        ## mysql/oracle/h2/oceanbase etc.
        db-type = "mysql"
        driver-class-name = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://127.0.0.1:3306/seata"
        user = "root"
        password = "123456"
      }
    }
    ```

  * registry.conf

    ```
    registry {
      # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
      type = "nacos"
    
      nacos {
        serverAddr = "192.168.0.101:3333"
        namespace = ""
        cluster = "default"
      }
      eureka {
        serviceUrl = "http://localhost:8761/eureka"
        application = "default"
        weight = "1"
      }
      redis {
        serverAddr = "localhost:6379"
        db = "0"
      }
      zk {
        cluster = "default"
        serverAddr = "127.0.0.1:2181"
        session.timeout = 6000
        connect.timeout = 2000
      }
      consul {
        cluster = "default"
        serverAddr = "127.0.0.1:8500"
      }
      etcd3 {
        cluster = "default"
        serverAddr = "http://localhost:2379"
      }
      sofa {
        serverAddr = "127.0.0.1:9603"
        application = "default"
        region = "DEFAULT_ZONE"
        datacenter = "DefaultDataCenter"
        cluster = "default"
        group = "SEATA_GROUP"
        addressWaitTime = "3000"
      }
      file {
        name = "file.conf"
      }
    }
    
    config {
      # file、nacos 、apollo、zk、consul、etcd3
      type = "file"
    
      nacos {
        serverAddr = "localhost"
        namespace = ""
      }
      consul {
        serverAddr = "127.0.0.1:8500"
      }
      apollo {
        app.id = "seata-server"
        apollo.meta = "http://192.168.1.204:8801"
      }
      zk {
        serverAddr = "127.0.0.1:2181"
        session.timeout = 6000
        connect.timeout = 2000
      }
      etcd3 {
        serverAddr = "http://localhost:2379"
      }
      file {
        name = "file.conf"
      }
    }
    
    ```

    

