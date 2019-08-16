package com.m520it.redis.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.m520it.redis.service.UserService;

public class RedisTemplateTest {

	@Test
	public void test1() {
		ClassPathXmlApplicationContext cpxc=new ClassPathXmlApplicationContext("springMVC.xml","springMVC_redis.xml");
		UserService service=cpxc.getBean(UserService.class);
		String key="users:1";
		String value=service.getString(key);
		System.out.println(value);
	}
}
