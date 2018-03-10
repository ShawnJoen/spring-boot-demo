package com.idea.example.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedissonService {
    //可以获取application.properties值
    private final Environment environment;
    //使用@Autowired RedissonClient需要配置Redisson @Configuration
    private final RedissonClient redissonClient;

    @Autowired
    public RedissonService(Environment environment, RedissonClient redissonClient) {
        this.environment = environment;
        this.redissonClient = redissonClient;
    }

    //定时执行 initialDelay(系统启动后 过10秒初始化开始计数),fixedDelay每过设定的毫秒单位触发一次
    @Scheduled(fixedDelay=15_000, initialDelay=10_000)
    private synchronized void setCron() {
        String key = environment.getActiveProfiles()[0] + "_env";
        log.info("-------------------setCron ready: {}", key);
        RLock lock = redissonClient.getLock(key);
        if (!lock.isLocked()) {
            //此次加锁过期时间，超过leaseTime还没有解锁的话就强制解锁
            lock.lock(15, TimeUnit.SECONDS);//[TimeUnit.SECONDS, TimeUnit.MINUTES]
            log.info("-------------------setCron begin");
//            try {
//                //假设处理业务61秒
//                Thread.sleep(80000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
            log.info("-------------------setCron end");
            lock.unlock();
        //    }
        }
    }

}
