# i-LogisticsSystem

> 2020年东软暑期实训项目 - - 物流配送管理系统

### RabbitMQ

1. 消费者：响应类加上`@RabbitListener`注解,响应方法上加上`@RabbitHandler`注解。

2. 生产者：`@Autowired`注入`AmqpTemplate`对象，使用`convertAndSend`方法。

3.具体在`test\java\com\tcsquad\ilogistics\mq`中有样例（为了防止被spring自动识别可能部分注解被注释）