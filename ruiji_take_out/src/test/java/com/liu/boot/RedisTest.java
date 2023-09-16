package com.liu.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @Author
 * @Date 2023/9/16 12:19
 * @Description
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","张三");

        String name = (String) valueOperations.get("name");
        System.out.println(name);

    }
}
