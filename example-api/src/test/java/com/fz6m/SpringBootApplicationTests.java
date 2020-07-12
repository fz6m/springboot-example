package com.fz6m;

import com.fz6m.entity.Goods;
import com.fz6m.utils.JsonUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JedisPool jedisPool;


    @Test
    public void test() {
//        测试 lombok 注解关闭资源
//        try {
//            for (int i = 1; i <= 50; i++) {
//                @Cleanup Jedis jedis = jedisPool.getResource();
//                System.out.println(i);
//                // doSomething
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

 

}
