package com.m520it.crud.bean;

import java.util.HashMap;
import java.util.Map;

public class JackSon {

private static String  succes;
private int code;
private Map<String, Object> extend=new HashMap<String, Object>();

public  static JackSon succes() {
	JackSon jk=new JackSon();
	jk.setCode(100);
	jk.setSucces("处理成功");
	return jk;
}
public  static JackSon fail() {
	JackSon jk=new JackSon();
	jk.setCode(200);
	jk.setSucces("处理失败");
	return jk;
}
public  JackSon add(String str,Object obj) {
	this.getExtend().put(str, obj);
	return this;
}


public  String getSucces() {
	return succes;
}
public  void setSucces(String succes) {
	this.succes = succes;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public Map<String, Object> getExtend() {
	return extend;
}
public void setExtend(Map<String, Object> extend) {
	this.extend = extend;
}


}
