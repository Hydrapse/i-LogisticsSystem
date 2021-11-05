# i-LogisticsSystem

> 2020年东软暑期实训项目 - - 物流配送管理系统

### 特别说明

本项目部分配置文件涉及密码没有放在公共仓库中，可在application.yml中自行添加：
```
spring:

  # 消息队列
  rabbitmq:
  
  # 数据库
  datasource:

# 阿里云对象存储
aliyun-oss:

# 百度地图sdk
map:
```
### 概要介绍

本项目使用JavaWeb技术栈, 采用vue+springboot进行前后端分离

界面交互性强, 后端功能强大, 同时提供丰富的API接口, 方便购物平台对接

### 功能模块

![项目结构图](https://user-images.githubusercontent.com/45584876/140468317-be3926b0-5534-430b-ba77-b6a3870992c5.png)

### 核心业务流程

<img src="https://user-images.githubusercontent.com/45584876/140468169-d98c3984-3762-45a9-8661-cd4fe80153e2.png" width="600">

### 技术架构

<img src="https://user-images.githubusercontent.com/45584876/140468454-5eb12472-a361-4b6b-b46b-eb66b1482107.png" width="400">

#### 消息队列 topic设计

1. 订单，topic： `order`，ack：需要，后端消费
2. 待审核订单，topic：`unreviewed order`，ack：需要
3. 退货审核，topic：`unreviewed returned item`，ack：需要
4. 换货审核，topic：`unreviewed exchange item`，ack：需要
5. 出库审核，topic： `unreviewed item out`，ack：需要
6. 入库审核，topic： `unreviewed item in`，ack：需要
7. 入库请求，topic： `item in`，ack：需要，后端消费
8. 缺货，topic： `lack item`，ack：需要，后端消费

