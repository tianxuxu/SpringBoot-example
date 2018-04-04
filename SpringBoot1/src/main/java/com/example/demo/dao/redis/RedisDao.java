package com.example.demo.dao.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Redis操作类
 * 
 * @author tianxuxu
 *
 */

@Repository
public class RedisDao {

	@Autowired
	private RedisConnectionFactory rFactory;

	@Autowired
	private StringRedisTemplate redisTemplate;

	// 普通redis实例
	//@Autowired
	//private RedisTemplate<String, Object> rTemplate;

	public void setKey(String key, String value) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ops.set(key, value, 1, TimeUnit.MINUTES);
	}

	public String getValue(String key) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		return ops.get(key);
	}
}
