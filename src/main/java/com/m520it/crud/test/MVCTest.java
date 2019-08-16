package com.m520it.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.m520it.crud.bean.Employee;

//模拟的测试需要servlet3.0的支持
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springMVC.xml" })
public class MVCTest {
	//传入springmvc的IOC容器
	@Autowired
	private WebApplicationContext context;
	// 虚拟mvc,获取处理的结果
	MockMvc mockMvc;

	//初始化方法,获取MockMvc
	@Before    //每次要使用,都需要初始化方法一次
	public void initMockMvc() {
      mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testMockMvc() throws Exception {
		//模拟请求拿到返回值
	MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
			.andReturn();
	MockHttpServletRequest request= result.getRequest();
	PageInfo pageInfo=(PageInfo) request.getAttribute("pageInfo");
	System.out.println("当前页的条数:" +pageInfo.getPageSize());
	System.out.println("当前页:"+pageInfo.getPageNum());
	System.out.println("总记录数:"+pageInfo.getTotal());
	System.out.println("总页码:"+pageInfo.getPages());
	System.out.println("在当前页需要连续显示的页码");
	int[] i=pageInfo.getNavigatepageNums();
	for (int j : i) {
		System.out.println(" "+j);
	}
	List<Employee> emp=pageInfo.getList();
	for (Employee employee : emp) {
		System.out.println(employee.getEmpId()+employee.getEmpName()+employee.getGender()+employee.getEmail());
	}
	}
	
	
}
