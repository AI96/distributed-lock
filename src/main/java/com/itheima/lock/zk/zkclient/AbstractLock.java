package com.itheima.lock.zk.zkclient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
@Slf4j
public abstract class AbstractLock {

    //zookeeper服务器地址
    public static final String ZK_SERVER_ADDR="127.0.0.1:2181";

    //zookeeper超时时间
    public static final int CONNECTION_TIME_OUT=30000;
    public static final int SESSION_TIME_OUT=30000;

    //创建zk客户端
    protected ZkClient zkClient = new ZkClient(ZK_SERVER_ADDR,SESSION_TIME_OUT,CONNECTION_TIME_OUT);

    /**
     * 获取锁
     * @return
     */
    public abstract boolean tryLock();

    /**
     * 等待加锁
     */
    public abstract void waitLock();

    /**
     * 释放锁
     */
    public abstract void releaseLock();

   //加锁实现
    public void getLock() {

        String threadName = Thread.currentThread().getName();

        if (tryLock()){
           log.info(threadName+":   获取锁成功");
        }else {
            log.info(threadName+":   等待获取锁");
            waitLock();
            getLock();
        }
    }
}