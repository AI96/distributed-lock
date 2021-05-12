package com.itheima.lock.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: yp
 */
@Configuration
public class CuratorLockConfig {

    //private String zkUrl = "192.168.133.129:2181,192.168.133.129:2182,192.168.133.129:2183";
    private String  zkUrl= "127.0.0.1:2181";
    // session 超时时间
    private int timeOut = 60000;
    // zkclient 重试间隔时间
    private int baseSleepTimeMs = 5000;
    //zkclient 重试次数
    private int retryCount = 5;

    @Bean
    public CuratorFramework client(){
        CuratorFramework  client = CuratorFrameworkFactory
                .builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(timeOut)
                .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, retryCount))
                .build();
        client.start();
        return client;
    }

    /**
     * 分布式可重入排它锁
     * @param client
     * @return
     */
    @Bean
    public InterProcessMutex interProcessMutex(CuratorFramework client){
        return new InterProcessMutex(client,"/lock");
    }

    /**
     * 分布式排它锁（非可重入锁）
     * @param client
     * @return
     */
    @Bean
    public InterProcessSemaphoreMutex interProcessSemaphoreMutex (CuratorFramework client){
        return new InterProcessSemaphoreMutex(client,"/lock");
    }

    /**
     * 分布式读写锁
     * @param client
     * @return
     */
    @Bean
    public InterProcessReadWriteLock interProcessReadWriteLock (CuratorFramework client){
        return new InterProcessReadWriteLock(client,"/lock");
    }

}
