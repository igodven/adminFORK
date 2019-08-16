package com.m520it.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {

	private static JedisPool pool;
	static {
		// 配置redis pool的基本信息
		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setMaxTotal(5);// 设置最大的连接数
		jedisConfig.setMaxIdle(1);// 设置最大的空闲数
		// 配置pool连接池
		String host = "192.168.43.2";
		int port = 6379;
		 pool = new JedisPool(jedisConfig, host, port);
	}
	public static Jedis jedisPool() {

		Jedis jedis = pool.getResource();
		jedis.auth("admin");
		return jedis;
	}
	
	public static void close(Jedis jedis) {
		jedis.close();
	}
}
