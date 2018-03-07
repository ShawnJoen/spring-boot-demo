# Spring Boot 基本功能实现
Spring Boot Intellij Idea Gradle Demo Project
# Mybatis 增删改查 API 
&nbsp;&nbsp;查询城市&nbsp;:&nbsp;/api/findOneCity?id=城市编号<br><br>
&nbsp;&nbsp;新建城市&nbsp;:&nbsp;/api/createCity?provinceId=上级城市编号&cityName=城市名称&description=描述<br><br>
&nbsp;&nbsp;修改城市&nbsp;:&nbsp;/api/updateCity?id=城市编号&provinceId=上级城市编号&cityName=城市名称&description=描述<br><br>
&nbsp;&nbsp;删除城市&nbsp;:&nbsp;/api/deleteCity/城市编号
# RabbitMq
&nbsp;&nbsp;Producer And Consumer &nbsp;:&nbsp;/mq/rabbit/sendMessageNoExchange<br><br>
&nbsp;&nbsp;发布/订阅 转发器类型[Fanout] &nbsp;:&nbsp;/mq/rabbit/sendMessageUsingFanoutNo1Exchange<br><br>
&nbsp;&nbsp;发布/订阅 转发器类型[Topic] &nbsp;:&nbsp;/mq/rabbit/sendMessageUsingTopicNo1Exchange<br><br>
&nbsp;&nbsp;发布/订阅 转发器类型[Topic] &nbsp;:&nbsp;/mq/rabbit/sendMessageUsingTopicStartExchange
# SockJS
&nbsp;&nbsp;通过@SendTo发布消息&nbsp;:&nbsp;/socket/pingPong<br><br>
&nbsp;&nbsp;通过SimpMessagingTemplate发布消息&nbsp;:&nbsp;/socket/simpleChat
