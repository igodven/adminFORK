package com.m520it.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m520it.crud.bean.Department;
import com.m520it.crud.dao.DepartmentMapper;
import com.m520it.crud.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DepartmentMapper mapper;
	@Override
	public List<Department> getDept() {
		List<Department> listDept=mapper.selectByExample(null);
		return listDept;
	}

}
