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

//ģ��Ĳ�����Ҫservlet3.0��֧��
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springMVC.xml" })
public class MVCTest {
	//����springmvc��IOC����
	@Autowired
	private WebApplicationContext context;
	// ����mvc,��ȡ����Ľ��
	MockMvc mockMvc;

	//��ʼ������,��ȡMockMvc
	@Before    //ÿ��Ҫʹ��,����Ҫ��ʼ������һ��
	public void initMockMvc() {
      mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testMockMvc() throws Exception {
		//ģ�������õ�����ֵ
	MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
			.andReturn();
	MockHttpServletRequest request= result.getRequest();
	PageInfo pageInfo=(PageInfo) request.getAttribute("pageInfo");
	System.out.println("��ǰҳ������:" +pageInfo.getPageSize());
	System.out.println("��ǰҳ:"+pageInfo.getPageNum());
	System.out.println("�ܼ�¼��:"+pageInfo.getTotal());
	System.out.println("��ҳ��:"+pageInfo.getPages());
	System.out.println("�ڵ�ǰҳ��Ҫ������ʾ��ҳ��");
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
