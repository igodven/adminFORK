package com.m520it.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.m520it.crud.bean.Department;
import com.m520it.crud.bean.Employee;
import com.m520it.crud.dao.DepartmentMapper;
import com.m520it.crud.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MapperTest {

	@Autowired
	private DepartmentMapper deptMapper;
	@Autowired 
	private EmployeeMapper empMapper;
	@Autowired
	private SqlSession sqlSession;
	@Test
	public void insertDept() {
		deptMapper.insert(new Department(null,"¿ª·¢²¿"));
	}
	@Test
	public void insertEmp() {
		EmployeeMapper employee=sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i <= 1000; i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			employee.insert(new Employee(null, uid, "M", uid+"com.m520it.qq.com", 1));
		}
	}
	
}
