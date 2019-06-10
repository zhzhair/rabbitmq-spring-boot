# rabbitmq-spring-boot
spring boot集成rabbitmq实现异步添加登录历史记录。
  producer项目实现登录的同时发送消息到rabbitMQ，用jmeter并发测试模拟登录；
  consumer消费rabbitMQ添加登录历史记录到别的数据库。
