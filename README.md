# Spring Boot 基本功能实现
Spring Boot Intellij Idea Gradle Demo Project
# Mybatis 增删改查 API 
&nbsp;&nbsp;查询城市&nbsp;:&nbsp;/api/findOneCity?id=城市编号<br><br>
&nbsp;&nbsp;查询城市&nbsp;:&nbsp;/api/findByCityName?cityName=城市名称<br><br>
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
# CrudRepository
&nbsp;&nbsp;·&nbsp;&nbsp;/cache/crud/getCityById?id=城市编号<br><br>
&nbsp;&nbsp;·&nbsp;&nbsp;/cache/crud/getCityByCityName?cityName=城市名称
# Redisson
&nbsp;&nbsp;获取所有的Keys&nbsp;:&nbsp;/redisson/RKeys<br><br>
&nbsp;&nbsp;存取任何类型的对象&nbsp;:&nbsp;/redisson/RBucket<br><br>
&nbsp;&nbsp;存取Map对象&nbsp;:&nbsp;/redisson/RMap<br><br>
&nbsp;&nbsp;存取有序集合&nbsp;:&nbsp;/redisson/RSortedSet<br><br>
&nbsp;&nbsp;存取集合&nbsp;:&nbsp;/redisson/RSet<br><br>
&nbsp;&nbsp;存取列表&nbsp;:&nbsp;/redisson/RList<br><br>
&nbsp;&nbsp;存取队列&nbsp;:&nbsp;/redisson/RQueue<br><br>
&nbsp;&nbsp;存取双端队列&nbsp;:&nbsp;/redisson/RDeque<br><br>
&nbsp;&nbsp;加解锁&nbsp;:&nbsp;/redisson/RLock<br><br>
&nbsp;&nbsp;存取原子数&nbsp;:&nbsp;/redisson/RAtomicLong<br><br>
&nbsp;&nbsp;获取记数锁&nbsp;:&nbsp;/redisson/RCountDownLatch<br><br>
&nbsp;&nbsp;挂载Topic订阅者&nbsp;:&nbsp;/redisson/RTopicSubscribe<br><br>
&nbsp;&nbsp;发布者Topic发布消息&nbsp;:&nbsp;/redisson/RTopicPublish
# FeignClient
&nbsp;&nbsp;通过FeignClient获取数据&nbsp;:&nbsp;/feign/github<br><br>
&nbsp;&nbsp;通过FeignClient获取指定数据&nbsp;:&nbsp;/feign/github/jack
# Spring Security
&nbsp;&nbsp;用户登入&nbsp;:&nbsp;/user/login<br><br>
&nbsp;&nbsp;用户注册&nbsp;:&nbsp;/user/register
# Email Service
&nbsp;&nbsp;发送邮件&nbsp;:&nbsp;/user/sendEmail?email=邮件地址
