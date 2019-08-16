package com.m520it.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m520it.crud.service.UserService;

@Controller
@RequestMapping("/redis")
public class RedisTemplateController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/getMessage")
	@ResponseBody
	public String test1() {
		String key="users:1";
		String value=service.getString(key);
		System.out.println(value);
		return value;
	}
}
