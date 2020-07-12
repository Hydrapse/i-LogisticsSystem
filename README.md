# i-LogisticsSystem

> 2020年东软暑期实训项目 - - 物流配送管理系统

### 概要介绍

本项目使用JavaWeb技术栈, 采用vue+springboot进行前后端分离

界面交互性强, 后端功能强大, 同时提供丰富的API接口, 方便购物平台对接

### 功能模块

### 消息队列

1. 订单，topic： `order`，ack：需要，后端消费
2. 待审核订单，topic：`unreviewed order`，ack：需要
3. 退货审核，topic：`unreviewed returned item`，ack：需要
4. 换货审核，topic：`unreviewed exchange item`，ack：需要
5. 出库审核，topic： `unreviewed item out`，ack：需要
6. 入库审核，topic： `unreviewed item in`，ack：需要
7. 入库请求，topic： `item in`，ack：需要，后端消费

