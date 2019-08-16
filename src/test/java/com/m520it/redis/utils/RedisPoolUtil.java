package com.m520it.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {

	private static JedisPool pool;
	static {
		// ����redis pool�Ļ�����Ϣ
		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setMaxTotal(5);// ��������������
		jedisConfig.setMaxIdle(1);// �������Ŀ�����
		// ����pool���ӳ�
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
