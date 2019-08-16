package com.m520it.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m520it.crud.bean.Employee;
import com.m520it.crud.bean.JackSon;
import com.m520it.crud.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	/*
	 * @RequestMapping("/emps") public String listAll(@RequestParam(name =
	 * "pn",defaultValue = "1")Integer pn,Model model) {
	 * //�ڷ�ҳǰ���÷�ҳ��Ϣ:��ѯ�ڼ�ҳ,����ÿһҳ��ʾ�������� PageHelper.startPage(pn, 5); List<Employee>
	 * listEmp=service.listAll(pn); //ÿҳ��ʾ5������ҳ,��װ����ϸ�ķ�ҳ��Ϣ,�������ǲ�ѯ������ PageInfo
	 * page=new PageInfo(listEmp,5); model.addAttribute("pageInfo", page); return
	 * "list"; }
	 */

	@RequestMapping("/emps")
	@ResponseBody
	public JackSon getEmpWithDept(@RequestParam(name = "pn", defaultValue = "1") Integer pn, Model model) {
		// �ڷ�ҳǰ���÷�ҳ��Ϣ:��ѯ�ڼ�ҳ,����ÿһҳ��ʾ��������
		PageHelper.startPage(pn, 5);
		List<Employee> listEmp = service.listAll(pn);
		// ÿҳ��ʾ5������ҳ,��װ����ϸ�ķ�ҳ��Ϣ,�������ǲ�ѯ������
		PageInfo page = new PageInfo(listEmp, 5);
		return JackSon.succes().add("pageInfo", page);
	}

	@RequestMapping("/saveEmps")
	@ResponseBody
	public JackSon saveEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println("������ֶ�" + error.getCode());
				System.out.println("�������Ϣ" + error.getDefaultMessage());
				map.put(error.getCode(), error.getDefaultMessage());
			}
			return JackSon.fail().add("error", map);
		} else {
			service.saveEmp(employee);
			return JackSon.succes();
		}
	}

	/**
	 * �����û�����У��
	 * 
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkEmpName")
	@ResponseBody
	public JackSon checkEmpName(String empName) {
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regx)) {
			return JackSon.fail().add("va_msg", "������2-5�����ĺ��ֻ���6-16��Ӣ���ַ�!");
		}
		Boolean b = service.check(empName);
		if (b) {
			return JackSon.succes();
		} else {
			return JackSon.fail().add("va_msg", "������2-5�����ĺ��ֻ���6-16��Ӣ���ַ�!");
		}
	}
	@RequestMapping("/getEmp/{id}")
	@ResponseBody
	public JackSon getEmployee(@PathVariable("id") Integer id) {
		Employee emp=service.getEmp(id);
		return JackSon.succes().add("emp", emp);
	}
	
	//���²���ʹ��put����
	@RequestMapping(value="/updateSaveEmp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public JackSon updateSaveEmp(Employee employee) {
	
		service.updateSaveEmp(employee);
		return JackSon.succes();
	}
	
	//ɾ������
	@RequestMapping("/emp/{ids}")
	@ResponseBody
	public JackSon deleteEmp(@PathVariable("ids") String ids) {
		List<Integer> listEmpIds=new ArrayList<Integer>();
		if(ids.length()==1) {
			Integer id=Integer.parseInt(ids);
			service.deleteEmp(id);
		}else {
		String [] empIds=ids.split("-");
		for (String string : empIds) {
			Integer empids= Integer.parseInt(string);
			listEmpIds.add(empids);
		}
		service.deleteBatch(listEmpIds);
		}
		return JackSon.succes();
	}
}
