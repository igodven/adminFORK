package com.m520it.crud.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.m520it.crud.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private RedisTemplate<String, String> template;
	
	/**string的具体操作
	 * 通过redisTemplate的模板进行操作
	 * 如果redis中有数据,就直接返回,
	 * 没有数据,则需要从数据库将数据查询出来,在保存在redis中
	 */
	@Override
	public String getString(String key) {
		//获取String的操作权:也就是对String类型的数据进行操作
		ValueOperations<String,String> vos=template.opsForValue();
		if(template.hasKey(key)) {
			System.out.println("在redis中取出数据");
			return vos.get(key);
		}else {
			String value="redisTemplte模板的联系";
			//将数据存储在redis中
			vos.set(key, value);
			System.out.println("从数据库中取出数据");
			return value;
		}
	}

}
