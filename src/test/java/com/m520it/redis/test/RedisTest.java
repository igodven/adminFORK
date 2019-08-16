package com.m520it.redis.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.m520it.redis.bean.User;
import com.m520it.redis.utils.RedisPoolUtil;

import redis.clients.jedis.Jedis;

public class RedisTest {
	/**
	 * java端通过jedis客户端操作Redis服务器
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Jedis jedis = RedisPoolUtil.jedisPool();
		// 通过ping值测试jedis是否连接成功
		System.out.println(jedis.ping());
		RedisPoolUtil.close(jedis);
	}

	/**
	 * 通过jedis的命令对redis的String进行操作 对字符串的数据的存储 将数据放在redis中进行存储
	 */
	@Test
	public void testString() {

		Jedis jedis = RedisPoolUtil.jedisPool();
		jedis.set("strName", "这是一个字符串的名称");
		String strName = jedis.get("strName");
		System.out.println(strName);
		// jedis的关闭
		RedisPoolUtil.close(jedis);

	}

	/**
	 * 判断redis中是否有这个查询的数据,如果有的话,直接从redis中取出来
	 * 如果redis中没有这个数据,就应该从mysql数据库中取出数据,并且缓存到redis中
	 */
	@Test
	public void test2() {

		Jedis jedis = RedisPoolUtil.jedisPool();
		String b = "bade";
		if (jedis.exists(b)) {
			String bValue = jedis.get(b);
			System.out.println("从redis中取出的值" + bValue);
		} else {
			String newValue = "设置的新值";
			jedis.set(b, newValue);
			String bValue = jedis.get(b);
			System.out.println("从数据库中取出来的值:" + bValue);
		}

		// jedis的关闭
		RedisPoolUtil.close(jedis);
	}

	/**
	 * 模拟redis中hash对象信息的存储过程 如果redis中有这个对象,就直接取出来
	 * 如果redis中没有这个对象,则需要从数据库中取出来,并保存到redis中
	 */
	@Test
	public void test3() {

		Jedis jedis = RedisPoolUtil.jedisPool();

		String key = "users:2";
		if (jedis.exists(key)) {
			Map<String, String> map = jedis.hgetAll(key);
			System.out.println("从redis中取出user:2的对象信息" + map);
		} else {
			jedis.hset(key, "id", "1");
			jedis.hset(key, "name", "张三");
			jedis.hset(key, "age", "22");
			jedis.hset(key, "password", "admin");
			Map<String, String> map = jedis.hgetAll(key);
			System.out.println("从数据库中取出对象信息并存储到redis中" + map);
		}
		RedisPoolUtil.close(jedis);
	}

	/**
	 * 模拟真实的情况,从数据库中取出数据,在加入到redis中进行缓存
	 */
	@Test
	public void test4() {
		Jedis jedis = RedisPoolUtil.jedisPool();
		User u = new User();
		String key = "users:20";
		if (jedis.exists(key)) {
			Map<String,String> hashMap=jedis.hgetAll(key);
			u.setId(Integer.parseInt(hashMap.get("id")));
			u.setName(hashMap.get("name"));
			u.setAge(Integer.parseInt(hashMap.get("age")));
			u.setPassword(hashMap.get("passwaord"));
			System.out.println("这是从redis中取出来的数据"+u.toString());
			
		} else {
			u.setId(20);
			u.setName("王五");
			u.setAge(22);
			u.setPassword("admin");
			// 通过hashmap的方式进行存储
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", u.getId() + "");
			map.put("name", u.getName());
			map.put("age", u.getAge() + "");
			map.put("password", u.getPassword());
			jedis.hmset(key, map);
			Map<String, String> value = jedis.hgetAll(key);
			System.out.println("这是从数据库中取出来阿德数据"+value);
		}

		RedisPoolUtil.close(jedis);
	}
}
