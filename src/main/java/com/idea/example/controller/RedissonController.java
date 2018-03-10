package com.idea.example.controller;

import com.idea.example.domain.City;
import com.idea.example.domain.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequestMapping("/redisson/")
public class RedissonController {

    //使用@Autowired RedissonClient需要配置Redisson @Configuration
    private final RedissonClient redissonClient;

    @Autowired
    public RedissonController(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /*
    * 获取所有的Keys
    * */
    @RequestMapping(value="RKeys")
    public @ResponseBody void RKeys() {
        log.info("-------------------RKeys begin");

        RKeys keys = redissonClient.getKeys();

//        Iterable<String> allKeys = keys.getKeys();
//        Iterator<String> allKeysIterator = allKeys.iterator();
//        while(allKeysIterator.hasNext()) {//遍历Redis上的所有的Key
//            log.info("RKeys All Keys: {}", allKeysIterator.next());
//        }

//        Iterable<String> foundedKeys = keys.getKeysByPattern("anyObjectKe*");
//        Iterator<String> foundedKeysIterator = foundedKeys.iterator();
//        while(foundedKeysIterator.hasNext()) {//遍历Redis上的匹配anyObjectKey*的Key
//            log.info("RKeys Founded Keys: {}", foundedKeysIterator.next());
//        }

//        long numOfDeletedKeys = keys.delete("anyObjectKey01", "testVo");//返回删除成功个数
//        log.info("RKeys num Of Deleted Keys: {}", numOfDeletedKeys);

        //?表示必须有一个(注意和正则的?不同),返回删除成功个数
//        long deletedKeysAmount = keys.deleteByPattern("anyObjectKe?");
//        log.info("RKeys deleted Keys Amount: {}", deletedKeysAmount);

//        String randomKey = keys.randomKey();
//        log.info("RKeys random Key: {}", randomKey);//获取随机Key

        long keysAmount = keys.count();
        log.info("RKeys keys Amount: {}", keysAmount);//Key的总个数

        log.info("-------------------RKeys end");
    }

    /*
    * 分布式RBucket是 存取任何类型的对象
    * */
    @RequestMapping(value="RBucket")
    public @ResponseBody void RBucket() {
        log.info("-------------------RBucket begin");

        RBucket<TestVo> bucket = redissonClient.getBucket("anyObjectKey06");
        //同步
        //bucket.set(new TestVo(4, 22, "Shawn4"));
        bucket.set(new TestVo(6, 24, "Shawn3"));//相同的Key存储时 最后的覆盖之前的
        //异步
        //bucket.setAsync(new TestVo(5, 23, "Shawn5"));

        log.info("-------------------RBucket end");
    }

    /*
    * 存取Map对象
    * */
    @RequestMapping(value="RMap")
    public @ResponseBody void RMap() throws Exception {
        log.info("-------------------RMap begin");

        RMap<String, Integer> rMap = redissonClient.getMap("testMap01");
        //清除集合 包括Key testMap01
        rMap.clear();

        //添加key-value 返回之前关联过的值
//        Integer firrtInteger = rMap.put("kkk1", 111);
//        System.out.println(firrtInteger);//null

        //添加key-value 返回之前关联过的值
//        Integer secondInteger = rMap.putIfAbsent("kkk2", 222);
//        System.out.println(secondInteger);//null

        //移除key-value
//        Integer thirdInteger = rMap.remove("kkk2");
//        System.out.println(thirdInteger);//222

//        boolean third = rMap.fastPut("kkk3", 333);
//        System.out.println(third);//true
//
//        Future<Boolean> fiveFuture = rMap.fastPutAsync("kkk4", 444);
//        System.out.println(fiveFuture.isDone());//由于是 异步的 同步判断会是 false

        //异步移除key
//        Future<Long> sixFuture = rMap.fastRemoveAsync("kkk4");
//        System.out.println(String.valueOf(sixFuture.get()));//1

        //遍历集合
        for (String key : rMap.keySet()) {
            System.out.println(key + ":" + rMap.get(key));
        }

        log.info("-------------------RMap end");
    }

    /*
    * 存取有序集合
    * */
    @RequestMapping(value="RSortedSet")
    public @ResponseBody void RSortedSet(){
        log.info("-------------------RSortedSet begin");

        RSortedSet<Integer> rSortedSet = redissonClient.getSortedSet("testSortedSet");
        //清除集合 包括Key testSortedSet
        rSortedSet.clear();
        //同步赋值
        rSortedSet.add(44);
        rSortedSet.add(12);
        //异步赋值
        rSortedSet.addAsync(77);
        rSortedSet.add(14);
        //输出时异步的可能还未存,只会输出同步的
        log.info(" RSortedSet: {}", Arrays.toString(rSortedSet.toArray()));//[12, 14, 44]

        log.info("-------------------RSortedSet end");
    }

    /*
    * 存取集合 去除重复并且自动排序
    * */
    @RequestMapping(value="RSet")
    public @ResponseBody void RSet(){
        log.info("-------------------RSet begin");

        RSet<Integer> rSet = redissonClient.getSet("testSet");
        //清除集合
//        rSet.clear();

//        Collection<Integer> arr = Arrays.asList(33,77,22,33,44,11);
//        rSet.addAll(arr);
//        rSet.add(55);
//        rSet.add(4);

        log.info(" RSet: {}", Arrays.toString(rSet.toArray()));//[4, 11, 22, 33, 44, 55, 77]

        log.info("-------------------RSet end");
    }

    /*
    * 存取列表 可以存重复值
    * */
    @RequestMapping(value="RList")
    public @ResponseBody void RList(){
        log.info("-------------------RList begin");

        RList<Integer> rList = redissonClient.getList("testList");
        //清除集合
        rList.clear();

        Collection<Integer> arr = Arrays.asList(33,77,22,33,44,11);
        rList.addAll(arr);
        rList.add(55);
        rList.add(4);

        log.info(" RList: {}", Arrays.toString(rList.toArray()));//[33, 77, 22, 33, 44, 11, 55, 4]

        log.info("-------------------RList end");
    }

    /*
    * 存取队列 先入先出
    * */
    @RequestMapping(value="RQueue")
    public @ResponseBody void RQueue(){
        log.info("-------------------RQueue begin");

        RQueue<Integer> rQueue = redissonClient.getQueue("testQueue");
        //清除队列
        rQueue.clear();
        Collection<Integer> arr = Arrays.asList(33,77,22,33,44,11);
        rQueue.addAll(arr);
        rQueue.add(55);
        rQueue.add(4);
        //查看队列元素
        System.out.println(rQueue.peek());//33 查看此次可以出的元素
        System.out.println(rQueue.element());//33 查看此次可以出的元素
        //移除队列元素
        System.out.println(rQueue.poll());//33 移除先进的元素同时输出
        System.out.println(rQueue.remove());//77 移除先进的元素同时输出
        //移除的元素以外的
        log.info(" RQueue: {}", Arrays.toString(rQueue.toArray()));//[22, 33, 44, 11, 55, 4]

        log.info("-------------------RQueue end");
    }

    /*
    * 存取双端队列 对头和队尾都可添加或者移除,也遵循队列的先入先出
    * */
    @RequestMapping(value="RDeque")
    public @ResponseBody void RDeque(){
        log.info("-------------------RDeque begin");

        RDeque<Integer> rDeque = redissonClient.getDeque("testDeque");
        //清空双端队列
        rDeque.clear();
        Collection<Integer> arr = Arrays.asList(33,77,22,33,44,11);
        rDeque.addAll(arr);
        rDeque.add(55);
        rDeque.add(4);
        //对头添加元素
        rDeque.addFirst(111);
        //队尾添加元素
        rDeque.addLast(999);
        System.out.println(Arrays.toString(rDeque.toArray()));//[111, 33, 77, 22, 33, 44, 11, 55, 4, 999]
        //查看对头元素
        System.out.println(rDeque.peek());//111
        System.out.println(rDeque.peekFirst());//111
        //查看队尾元素
        System.out.println(rDeque.peekLast());//999
        System.out.println(Arrays.toString(rDeque.toArray()));//[111, 33, 77, 22, 33, 44, 11, 55, 4, 999]
        //移除对头元素
        System.out.println(rDeque.poll());//111
        System.out.println(rDeque.pollFirst());//33
        //移除队尾元素
        System.out.println(rDeque.pollLast());//999
        System.out.println(Arrays.toString(rDeque.toArray()));//[77, 22, 33, 44, 11, 55, 4]
        //添加队尾元素
        System.out.println(rDeque.offer(9));//队尾 true
        System.out.println(rDeque.offerFirst(8));//队头 true
        System.out.println(Arrays.toString(rDeque.toArray()));//[8, 77, 22, 33, 44, 11, 55, 4, 9]
        //移除对头元素
        System.out.println(rDeque.pop());//8
        //显示双端队列的元素
        System.out.println(Arrays.toString(rDeque.toArray()));//[77, 22, 33, 44, 11, 55, 4, 9]

        log.info("-------------------RDeque end");
    }

//    @RequestMapping(value="RBlockingQueue")
//    public @ResponseBody void RBlockingQueue(){
//        log.info("-------------------RBlockingQueue begin");
//
//        log.info("-------------------RBlockingQueue end");
//    }

    /*
    * 加解锁 string中存放 线程标示、线程计数
    * */
    @RequestMapping(value="RLock")
    public synchronized @ResponseBody void RLock(){
        log.info("-------------------RLock begin");

        RLock rLock = redissonClient.getLock("testLock");
        if (rLock.isLocked()) {
            rLock.unlock();
            log.info(" RLock 解锁");
        } else {
            //rLock.lock();
            rLock.lock(6, TimeUnit.SECONDS);
            log.info(" RLock 加锁");
        }

        System.out.println(rLock.getName());//testLock
        System.out.println(rLock.getHoldCount());//1 :表示有在加锁状态内的 锁
        System.out.println(rLock.isLocked());//true : 未解锁状态重复操作会 进入睡眠等待线程

        log.info("-------------------RLock end");
    }

    /*
    * 存取原子数 并且加减等
    * */
    @RequestMapping(value="RAtomicLong")
    public @ResponseBody void RAtomicLong(){
        log.info("-------------------RAtomicLong begin");

        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("testAtomicLong");
        rAtomicLong.set(300);
        System.out.println(rAtomicLong.addAndGet(200));//500
        System.out.println(rAtomicLong.decrementAndGet());//499 :减1并输出
        System.out.println(rAtomicLong.get());//499 :原样输出
        System.out.println(rAtomicLong.incrementAndGet());//500
        System.out.println(rAtomicLong.get());//500 :加1并输出
        log.info("-------------------RAtomicLong end");
    }

    /*
    * 获取记数锁 闭锁--等待其他线程中的操作都做完 在进行操作
    * */
    @RequestMapping(value="RCountDownLatch")
    public @ResponseBody void RCountDownLatch() throws Exception {
        log.info("-------------------RCountDownLatch begin");

        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch("testCountDownLatch");
        System.out.println(rCountDownLatch.getCount());//1

        //rCountDownLatch.trySetCount(1l);
        System.out.println(rCountDownLatch.getCount());//1

        //进入10秒等待线程 10后唤醒
        rCountDownLatch.await(10, TimeUnit.SECONDS);

        System.out.println(rCountDownLatch.getCount());//1

        log.info("-------------------RCountDownLatch end");
    }

    /*
    * 挂载Topic订阅者等待发布消息
    * */
    @RequestMapping(value="RTopicSubscribe")
    public @ResponseBody void RTopicSubscribe() throws Exception {
        log.info("-------------------RTopicSubscribe begin");

        RTopic<String> rTopic = redissonClient.getTopic("testTopic");
        rTopic.addListener(new MessageListener<String>() {

            @Override
            public void onMessage(String channel, String message) {
                System.out.println("订阅者 channel:" + channel);//订阅者 channel:testTopic
                System.out.println("订阅者 接收消息:" + message);//订阅者 接收消息:发布者 发送消息
            }
        });
        //等待发布者发布消息
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch("testCountDownLatch");
        rCountDownLatch.trySetCount(1);
        rCountDownLatch.await();

        log.info("-------------------RTopicSubscribe end");
    }

    /*
    * 发布者Topic发布消息
    * */
    @RequestMapping(value="RTopicPublish")
    public @ResponseBody void RTopicPublish() {
        log.info("-------------------RTopicPublish begin");

        RTopic<String> rTopic = redissonClient.getTopic("testTopic");
        /*
        * 1.开启RTopicSubscribe 再开启RTopicPublish 返回1
        * 1.未开启RTopicSubscribe 再开启RTopicPublish 返回0
        * */
        System.out.println(rTopic.publish("发布者 发送消息"));
        //发送完消息后 让订阅者不再等待
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch("testCountDownLatch");
        rCountDownLatch.countDown();

        log.info("-------------------RTopicPublish end");
    }

}
