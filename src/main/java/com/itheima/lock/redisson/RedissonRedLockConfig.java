/*
package com.itheima.lock.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonRedLockConfig {


    @Bean
    public RedissonClient redisClient01() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://192.168.200.150:7000";
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public RedissonClient redisClient02() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://192.168.200.150:7001";
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public RedissonClient redisClient03() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://192.168.200.150:7003";
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public RedissonClient redisClient04() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://192.168.200.150:7004";
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public RedissonClient redisClient05() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://192.168.200.150:7004";
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RedissonRedLock initRedissonClient(String lockKey){
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://192.168.200.150:7000").setDatabase(0);
        RedissonClient redissonClient1 = Redisson.create(config1);

        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://192.168.200.150:7001").setDatabase(0);
        RedissonClient redissonClient2 = Redisson.create(config2);

        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://192.168.200.150:7002").setDatabase(0);
        RedissonClient redissonClient3 = Redisson.create(config3);

        Config config4 = new Config();
        config4.useSingleServer().setAddress("redis://192.168.200.150:7003").setDatabase(0);
        RedissonClient redissonClient4 = Redisson.create(config4);

        Config config5 = new Config();
        config5.useSingleServer().setAddress("redis://192.168.200.150:7004").setDatabase(0);
        RedissonClient redissonClient5 = Redisson.create(config5);

        RLock rLock1 = redissonClient1.getLock(lockKey);
        RLock rLock2 = redissonClient2.getLock(lockKey);
        RLock rLock3 = redissonClient3.getLock(lockKey);
        RLock rLock4 = redissonClient4.getLock(lockKey);
        RLock rLock5 = redissonClient5.getLock(lockKey);

        RedissonRedLock redissonRedLock = new RedissonRedLock(rLock1,rLock2,rLock3,rLock4,rLock5);

        return redissonRedLock;
    }
}*/
