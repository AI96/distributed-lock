package com.itheima.lock.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RedissonLock {

    @Autowired
    private RedissonClient redissonClient;

    /***
     * 加锁,会一直循环加锁，直到拿到锁
     * @param lockkey
     * @return
     */
    public RLock lock(String lockkey) {
        RLock lock = redissonClient.getLock(lockkey);
        lock.lock();
        return lock;
    }
    /***
     * 加锁,在指定时间内自动释放锁
     * @param lockkey
     * @return
     */
    
    public RLock lock(String lockkey, long timeout) {
        RLock lock = redissonClient.getLock(lockkey);
        lock.lock(timeout,TimeUnit.SECONDS);
        return lock;
    }

    /***
     * 加锁,在指定时间内自动释放锁(指定单位)
     * @param lockkey
     * @return
     */
    
    public RLock lock(String lockkey, long timeout, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockkey);
        lock.lock(timeout,unit);
        return lock;
    }

    /***
     * 加锁,在指定时间内拿不到锁就会放弃,如果拿到锁，锁最终有效时间为leasetime
     * @param lockkey
     * @return
     */
    
    public boolean tryLock(String lockkey, long timeout, long leasetime, TimeUnit unit) {
        return false;
    }

    /****
     * 解锁
     * @param lockkey
     */
    
    public void unLock(String lockkey) {
        RLock lock = redissonClient.getLock(lockkey);
        lock.unlock();
    }

    /***
     * 解锁
     * @param lock
     */
    
    public void unLocke(RLock lock) {
        lock.unlock();
    }
}
