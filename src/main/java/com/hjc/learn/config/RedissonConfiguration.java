package com.hjc.learn.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置类
 *
 * @author houjichao
 */
@Configuration
public class RedissonConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * redisson client
     *
     * @return bean
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (null == cluster) {
            String host = redisProperties.getHost();
            int port = redisProperties.getPort();
            String address = "redis://".concat(host.concat(":")).concat(String.valueOf(port));
            config.useSingleServer()
                    .setAddress(address).setPassword(redisProperties.getPassword());
        } else {
            String[] address = cluster.getNodes()
                    .stream().map("redis://"::concat)
                    .toArray(String[]::new);
            config.useClusterServers()
                    .addNodeAddress(address)
                    .setPassword(redisProperties.getPassword())
                    .setReadMode(ReadMode.MASTER);
        }
        return Redisson.create(config);
    }
}
