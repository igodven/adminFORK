package com.m520it.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m520it.crud.bean.Department;
import com.m520it.crud.bean.JackSon;
import com.m520it.crud.service.DeptService;

@Controller
public class DepartmentController {

	@Autowired
	private  DeptService service;
	@RequestMapping("/depts")
	@ResponseBody
	public JackSon getDept() {
		List<Department> listDept=service.getDept();
		return JackSon.succes().add("depts", listDept);
	}
}
