package com.m520it.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.m520it.crud.bean.Employee;
import com.m520it.crud.bean.EmployeeExample;
import com.m520it.crud.bean.EmployeeExample.Criteria;
import com.m520it.crud.dao.EmployeeMapper;
import com.m520it.crud.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeMapper mapper;
	@Override
	public List<Employee> listAll(Integer pn) {
		List<Employee> listEmp=mapper.selectByExampleWithDept(null);
		
		return listEmp;
	}
	@Override
	public void saveEmp(Employee employee) {

		mapper.insertSelective(employee);
	}
	@Override
	public Boolean check(String empName) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		Long count=mapper.countByExample(example);
		return count==0;
	}
	@Override
	public Employee getEmp(Integer id) {

		Employee emp=mapper.selectByPrimaryKey(id);
		return emp;
	}
	@Override
	public void updateSaveEmp(Employee employee) {

		mapper.updateByPrimaryKeySelective(employee);
	}
	@Override
	public void deleteEmp(Integer id) {

		mapper.deleteByPrimaryKey(id);
	}
	@Override
	public void deleteBatch(List<Integer> empIds) {

		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpIdIn(empIds);
		mapper.deleteByExample(example);
	}

}
