package com.fz6m.utils;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {

    @Autowired
    JedisPool jedisPool;


    /**
     * 向Redis中存String值，永久有效
     */
    public String set(String key, String value) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向Redis中存Object值，永久有效
     */
    public String set(String key, Object value) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.set(key, JsonUtil.toJSON(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入Key获取指定String Value
     */
    public String get(String key) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入Key获取指定Object Value
     */
    public <T> T get(String key, Class<T> cls) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return JsonUtil.parse(jedis.get(key), cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除指定Key-Value
     */
    public Long delete(String key) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向Redis中存String值，带有效期
     */
    public String set(String key, String value, int second) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            String set = jedis.set(key, value);
            jedis.expire(key, second);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向Redis中存Object值，带有效期
     */
    public String set(String key, Object value, int second) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            String set = jedis.set(key, JsonUtil.toJSON(value));
            jedis.expire(key, second);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取key数据项的剩余时间（秒）
     */
    public Long ttl(String key) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除键为key属性项的生存时间限制
     */
    public Long persist(String key) {
        try {
            @Cleanup Jedis jedis = jedisPool.getResource();
            return jedis.persist(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
