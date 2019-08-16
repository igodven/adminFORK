package com.m520it.redis.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.m520it.redis.bean.User;
import com.m520it.redis.utils.RedisPoolUtil;

import redis.clients.jedis.Jedis;

public class RedisTest {
	/**
	 * java��ͨ��jedis�ͻ��˲���Redis������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Jedis jedis = RedisPoolUtil.jedisPool();
		// ͨ��pingֵ����jedis�Ƿ����ӳɹ�
		System.out.println(jedis.ping());
		RedisPoolUtil.close(jedis);
	}

	/**
	 * ͨ��jedis�������redis��String���в��� ���ַ��������ݵĴ洢 �����ݷ���redis�н��д洢
	 */
	@Test
	public void testString() {

		Jedis jedis = RedisPoolUtil.jedisPool();
		jedis.set("strName", "����һ���ַ���������");
		String strName = jedis.get("strName");
		System.out.println(strName);
		// jedis�Ĺر�
		RedisPoolUtil.close(jedis);

	}

	/**
	 * �ж�redis���Ƿ��������ѯ������,����еĻ�,ֱ�Ӵ�redis��ȡ����
	 * ���redis��û���������,��Ӧ�ô�mysql���ݿ���ȡ������,���һ��浽redis��
	 */
	@Test
	public void test2() {

		Jedis jedis = RedisPoolUtil.jedisPool();
		String b = "bade";
		if (jedis.exists(b)) {
			String bValue = jedis.get(b);
			System.out.println("��redis��ȡ����ֵ" + bValue);
		} else {
			String newValue = "���õ���ֵ";
			jedis.set(b, newValue);
			String bValue = jedis.get(b);
			System.out.println("�����ݿ���ȡ������ֵ:" + bValue);
		}

		// jedis�Ĺر�
		RedisPoolUtil.close(jedis);
	}

	/**
	 * ģ��redis��hash������Ϣ�Ĵ洢���� ���redis�����������,��ֱ��ȡ����
	 * ���redis��û���������,����Ҫ�����ݿ���ȡ����,�����浽redis��
	 */
	@Test
	public void test3() {

		Jedis jedis = RedisPoolUtil.jedisPool();

		String key = "users:2";
		if (jedis.exists(key)) {
			Map<String, String> map = jedis.hgetAll(key);
			System.out.println("��redis��ȡ��user:2�Ķ�����Ϣ" + map);
		} else {
			jedis.hset(key, "id", "1");
			jedis.hset(key, "name", "����");
			jedis.hset(key, "age", "22");
			jedis.hset(key, "password", "admin");
			Map<String, String> map = jedis.hgetAll(key);
			System.out.println("�����ݿ���ȡ��������Ϣ���洢��redis��" + map);
		}
		RedisPoolUtil.close(jedis);
	}

	/**
	 * ģ����ʵ�����,�����ݿ���ȡ������,�ڼ��뵽redis�н��л���
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
			System.out.println("���Ǵ�redis��ȡ����������"+u.toString());
			
		} else {
			u.setId(20);
			u.setName("����");
			u.setAge(22);
			u.setPassword("admin");
			// ͨ��hashmap�ķ�ʽ���д洢
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", u.getId() + "");
			map.put("name", u.getName());
			map.put("age", u.getAge() + "");
			map.put("password", u.getPassword());
			jedis.hmset(key, map);
			Map<String, String> value = jedis.hgetAll(key);
			System.out.println("���Ǵ����ݿ���ȡ������������"+value);
		}

		RedisPoolUtil.close(jedis);
	}
}
