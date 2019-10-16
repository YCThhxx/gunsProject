package com.cskaoyan.guns.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author Tyj
 * @date 2019/10/15 20:03
 */
@Configuration
public class JedisConfig {

    @Bean
    public Jedis initJedis(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis;
    }
}
