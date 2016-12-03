package com.rc.openapi.util;
/**
 * @author 作者 尹滨  E-mail:yinbinhome@163.com
 * @version 创建时间：2010-7-14 上午10:02:39
 * 类说明
 */
public class Person {
	private String name;
	private String age;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Person(String name, String age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
}
