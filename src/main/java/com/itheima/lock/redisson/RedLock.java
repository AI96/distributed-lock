/*
package com.itheima.lock.redisson;

import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


//@Component
public class RedLock {

   //@Autowired
    private RedissonClient redissonClient01;
    private RedissonClient redissonClient02;
    private RedissonClient redissonClient03;
    private RedissonClient redissonClient04;
    private RedissonClient redissonClient05;

    */
/***
     * 加锁,会一直循环加锁，直到拿到锁
     * @param lockKey
     * @return
     *//*

    public RedissonRedLock lock(String lockKey) {
        RLock rLock1 = redissonClient01.getLock(lockKey);
        RLock rLock2 = redissonClient02.getLock(lockKey);
        RLock rLock3 = redissonClient03.getLock(lockKey);
        RLock rLock4 = redissonClient04.getLock(lockKey);
        RLock rLock5 = redissonClient05.getLock(lockKey);
        RedissonRedLock redissonRedLock = new RedissonRedLock(rLock1,rLock2,rLock3,rLock4,rLock5);
        return redissonRedLock;
    }

}
*/
