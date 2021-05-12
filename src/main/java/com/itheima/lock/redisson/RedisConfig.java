package com.itheima.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    /**
     * RedissonClient 单机配置
     * @return
     * @throws IOException
     */
    @Bean
    public RedissonClient redisClient() throws IOException {
        RedissonClient redissonClient;
        Config config = new Config();
        String url = "redis://" + host + ":" + port;
        config.useSingleServer().setAddress(url);
        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * RedissonClient 集群配置
     * @return
     * @throws IOException
     */
    /*@Bean
    public RedissonClient redisClient() throws IOException {
        //1.加载配置文件
        ClassPathResource resource = new ClassPathResource("redisson.yml");
        //2.解析配置文件
        Config config = Config.fromYAML(resource.getInputStream());
        //3.创建RedissonClient
        return Redisson.create(config);
    }*/



}