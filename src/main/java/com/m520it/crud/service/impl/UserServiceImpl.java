package com.m520it.crud.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.m520it.crud.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private RedisTemplate<String, String> template;
	
	/**string�ľ������
	 * ͨ��redisTemplate��ģ����в���
	 * ���redis��������,��ֱ�ӷ���,
	 * û������,����Ҫ�����ݿ⽫���ݲ�ѯ����,�ڱ�����redis��
	 */
	@Override
	public String getString(String key) {
		//��ȡString�Ĳ���Ȩ:Ҳ���Ƕ�String���͵����ݽ��в���
		ValueOperations<String,String> vos=template.opsForValue();
		if(template.hasKey(key)) {
			System.out.println("��redis��ȡ������");
			return vos.get(key);
		}else {
			String value="redisTemplteģ�����ϵ";
			//�����ݴ洢��redis��
			vos.set(key, value);
			System.out.println("�����ݿ���ȡ������");
			return value;
		}
	}

}
