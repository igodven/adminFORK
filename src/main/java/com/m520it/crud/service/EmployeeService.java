package com.m520it.crud.service;

import java.util.List;

import com.m520it.crud.bean.Employee;

public interface EmployeeService {

	List<Employee> listAll(Integer pn);

	void saveEmp(Employee employee);

	Boolean check(String empName);

	Employee getEmp(Integer id);

	void updateSaveEmp(Employee employee);

	void deleteEmp(Integer id);

	void deleteBatch(List<Integer> listEmpIds);

}
